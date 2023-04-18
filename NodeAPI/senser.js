const express = require("express");
const sensor = require("./senserDB");
const router = express.Router();

// get all sensors...............................................
router.get("/sensors", async (req, res) => {
  let sensors = await sensor.find();
  let response = [];
  sensors.forEach((sensor) => {
    let result = getResponseSensorOB(sensor);
    response.push(result);
  });
  res.setHeader("Content-Type", "application/json");
  res.status(200).json(response);
});

// add sensor.....................................................
router.post("/add", async (req, res) => {
  let sensorOB = new sensor({
    sensor_id: req.body.id,
    location_floorNo: req.body.location_floorNo,
    location_roomNo: req.body.location_roomNo,
    co2_level: req.body.co2_level,
    smoke_level: req.body.smoke_level,
    status: req.body.status,
  });
  try {
    await sensorOB.save();
    res.json({ msg: "done" }), 200;
  } catch (error) {
    res.json({ msg: "error saving data" }), 400;
  }
});

// update sensor..........
router.put("/update/:id", async (req, res) => {
  console.log(req.body.status);

  let sensorOB = await sensor.findOne({ sensor_id: req.params.id });

  (sensorOB.sensor_id = req.body.id),
    (sensorOB.location_floorNo = req.body.location_floorNo),
    (sensorOB.location_roomNo = req.body.location_roomNo),
    (sensorOB.co2_level = req.body.co2_level),
    (sensorOB.smoke_level = req.body.smoke_level),
    (sensorOB.status = req.body.status);

  await sensorOB
    .save()
    .then(() => {
      console.log("update done");
      return res.status(200).json({ data: "Successfull" });
    })
    .catch((error) => {
      console.log("Error occured");
      return res.json({ msg: error }), 400;
    });
});

// get single sensor.........
router.get("/sensors/:id", async (req, res) => {
  try {
    let sensorOB = await sensor.findOne({ sensor_id: req.params.id });
    let response = getResponseSensorOB(sensorOB);
    res.json(response), 200;
  } catch (error) {
    res.json({ msg: "invalid data" }), 400;
  }
});

// delete single sensor.........
router.delete("/delete/:id", async (req, res) => {
  try {
    let sensorOB = await sensor.findOneAndRemove({ sensor_id: req.params.id });
    res.json({ msg: "delete successfull" }), 200;
  } catch (error) {
    res.json({ msg: "invalid data" }), 400;
  }
});

function getResponseSensorOB(mongoSensorOB) {
  let sensor = {
    id: mongoSensorOB.sensor_id,
    location_floorNo: mongoSensorOB.location_floorNo,
    location_roomNo: mongoSensorOB.location_roomNo,
    co2_level: mongoSensorOB.co2_level,
    smoke_level: mongoSensorOB.smoke_level,
    status: mongoSensorOB.status,
  };
  return sensor;
}

module.exports = router;
