const express = require("express");
const mongoose = require("mongoose");

const sensor = require("./senser");
const app = express();

app.use(express.json());

app.use("/api/fireAlarmSystem", sensor);

//databse connection
mongoose.connect("mongodb://13.235.122.150:2017/sensor_node", {
  useNewUrlParser: true,
  useUnifiedTopology: true,
});
const db = mongoose.connection;
db.on("error", (error) => console.error(error));
db.once("open", () => console.log("connected to mongoose"));

app.listen(5000, () => {
  console.log("server is runnng....");
});
