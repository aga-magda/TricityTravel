const functions = require('firebase-functions');
const admin = require('firebase-admin');
const Client = require('node-rest-client-promise').Client;

const URL_stopsintrips = "http://91.244.248.30/dataset/c24aa637-3619-4dc2-a171-a23eec8f2172/resource/3115d29d-b763-4af5-93f6-763b835967d6/download/stopsintrips.json";
const URL_stops = "http://91.244.248.30/dataset/c24aa637-3619-4dc2-a171-a23eec8f2172/resource/4c4025f0-01bf-41f7-a39f-d156d201b82b/download/stops.json";
const URL_trips = "http://91.244.248.19/dataset/c24aa637-3619-4dc2-a171-a23eec8f2172/resource/33618472-342c-4a4a-ba88-a911ec0ad5a7/download/trips.json";

const client = new Client();
admin.initializeApp(functions.config().firebase);
var db = admin.firestore();

exports.downloadBusStopsFromFirebase = functions.https.onRequest((req, res) => {
    let docRef = db.collection('TricityTravel').doc('stops');
    docRef.get()
        .then(doc => {
            if (doc.exists) {
                res.send(doc.data());
            } else {
                console.log('No such document!');
            }
        })
        .catch(err => {
            console.log('Error getting document', err);
        });
})

exports.createBusStopsJson = functions.https.onRequest((req, res) => {
    let promises = [getJson(URL_stops), getJson(URL_stopsintrips), getJson(URL_trips)];
    Promise.all(promises)
        .then(results => {
            var todayDate = getTodayDate();
            var firstJson = results[0][todayDate].stops;
            var secondJson = results[1][todayDate].stopsInTrip;
            var thirdJson = results[2][todayDate].trips;

            var stopList = {};
            var key = "stops";
            stopList["stops"] = [];

            var listOfStopId = [];

            var index = {};
            for (let j in secondJson) {
                let obj = secondJson[j];

                if (!listOfStopId.includes(obj.stopId)) {
                    listOfStopId.push(obj.stopId);
                    index[obj.stopId] = obj;
                    index[obj.stopId].routeIds = [];
                    index[obj.stopId].tripIds = [];
                    index[obj.stopId].routeIds.push(obj.routeId);
                    index[obj.stopId].tripIds.push(obj.tripId);
                } else {
                    if (!index[obj.stopId].routeIds.includes(obj.routeId)) {
                        index[obj.stopId].routeIds.push(obj.routeId);
                        index[obj.stopId].tripIds.push(obj.tripId);
                    }
                }
            }

            for (let i in firstJson) {
                let firstObj = firstJson[i];
                let secondObj = index[firstObj.stopId];
                let directions = [];
                for (let j = 0; j < secondObj.tripIds.length; j++) {
                    let rAndTId = "R" + secondObj.routeIds[j] + "T" + secondObj.tripIds[j];
                    let tripHeadsign = thirdJson.find(x => x.id === rAndTId).tripHeadsign;
                    directions.push(transformHeadsign(tripHeadsign));
                }
                if (secondObj) {
                    stopList[key].push({
                        "stopDesc": firstObj.stopDesc,
                        "stopId": firstObj.stopId,
                        "routeIds": secondObj.routeIds,
                        "directions": directions
                    });
                }
            }

            let docRef = db.collection('TricityTravel').doc('stops');
            docRef.set(stopList);

            res.send("Stops refreshed");
        })
        .catch(err => console.log(err));
})

async function getJson(url) {
    let response = await client.getPromise(url);
    let json = await response.data;
    return json;
}

function transformHeadsign(headsign) {
    if (headsign.includes(">")) {
        headsign = headsign.substring(headsign.indexOf(">") + 2);
    }
    else if (headsign.includes("-")) {
        headsign = headsign.substring(headsign.indexOf("-") + 2);
    }
    return headsign;
}

function getTodayDate() {
    let today = new Date();
    let dd = today.getDate();
    let mm = today.getMonth() + 1;

    let yyyy = today.getFullYear();
    if (dd < 10) {
        dd = '0' + dd;
    }
    if (mm < 10) {
        mm = '0' + mm;
    }
    return yyyy + '-' + mm + '-' + dd;
}
