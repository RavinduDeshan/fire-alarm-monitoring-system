"use strict";
const express = require("express");
const sensorRoute = express.Router();

require("dotenv").config();
const {
  callService,
  getService,
} = require("../serviceDiscover/serviceContoller");

// card services

sensorRoute.post("/sensor/add", async (req, res) => {
  let version = "1.0.0";

  const { ip, port } = await getService("sensor-service", version);

  let response = await callService({
    method: "post",
    url: `http://${ip}:${port}/sensor/add`,
    data: req.body,
  }).catch((err) => console.log(err));

  if (response) {
    if (response.status == 200)
      return res
        .status(200)
        .json({ data: response.data.data ? response.data.data : "success" });
    if (response.status == 400)
      return res.status(400).json({ error: response.data.error });
  }
  // }

  return res
    .status(400)
    .json({ error: "unknown error occured at API gateway" });
});

sensorRoute.post("/sensor/activate/:id", async (req, res) => {
  let version = "1.0.0";

  const { ip, port } = await getService("sensor-service", version);

  let response = await callService({
    method: "post",
    url: `http://${ip}:${port}/sensor/activate/${req.params.id}`,
    data: req.body,
  }).catch((err) => console.log(err));

  if (response) {
    if (response.status == 200)
      return res
        .status(200)
        .json({ data: response.data.data ? response.data.data : "success" });
    if (response.status == 400)
      return res.status(400).json({ error: response.data.error });
  }
  // }

  return res
    .status(400)
    .json({ error: "unknown error occured at service gateway" });
});

sensorRoute.post("/sensor/deactivate/:id", async (req, res) => {
  let version = "1.0.0";

  const { ip, port } = await getService("sensor-service", version);

  let response = await callService({
    method: "post",
    url: `http://${ip}:${port}/sensor/deactivate/${req.params.id}`,
    data: req.body,
  }).catch((err) => console.log(err));

  if (response) {
    if (response.status == 200)
      return res
        .status(200)
        .json({ data: response.data.data ? response.data.data : "success" });
    if (response.status == 400)
      return res.status(400).json({ error: response.data.error });
  }
  // }

  return res
    .status(400)
    .json({ error: "unknown error occured at service gateway" });
});

sensorRoute.post("/sensor/update/:id", async (req, res) => {
  let version = "1.0.0";
  const { ip, port } = await getService("sensor-service", version);

  let response = await callService({
    method: "post",
    url: `http://${ip}:${port}/sensor/update/${req.params.id}`,
    data: req.body,
  }).catch((err) => console.log(err));

  if (response) {
    if (response.status == 200)
      return res
        .status(200)
        .json({ data: response.data.data ? response.data.data : "success" });
    if (response.status == 400)
      return res.status(400).json({ error: response.data.error });
  }
  return res
    .status(400)
    .json({ error: "unknown error occured at API gateway" });
});

sensorRoute.get("/card/getall", async (req, res) => {
  let version = "1.0.0";
  const { ip, port } = await getService("sensor-service", version);

  let response = await callService({
    method: "get",
    // url: `http://${ip}:${port}/card/getall`,
    url: `http://${ip}:${port}/api/fireAlarmSystem/sensors`,
    
  });

  if (response) {
    if (response.status == 200)
      return res
        .status(200)
        .json({ data: response.data.data ? response.data.data : "success" });
    if (response.status == 400)
      return res.status(400).json({ error: response.data.error });
  }
  return res
    .status(400)
    .json({ error: "unknown error occured at API gateway" });
});

sensorRoute.get("/sensor/:id", async (req, res) => {
  let version = "1.0.0";
  const { ip, port } = await getService("sensor-service", version);

  let response = await callService({
    method: "get",
    url: `http://${ip}:${port}/sensor/${req.params.id}`,
  }).catch((err) => console.log(err));

  if (response) {
    if (response.status == 200)
      return res
        .status(200)
        .json({ data: response.data.data ? response.data.data : "success" });
    if (response.status == 400)
      return res.status(400).json({ error: response.data.error });
  }
  return res
    .status(400)
    .json({ error: "unknown error occured at API gateway" });
});

sensorRoute.get("/sensors/", async (req, res) => {
  let version = "1.0.0";
  const { ip, port } = await getService("sensor-service", version);

  let response = await callService({
    method: "get",
    url: `http://${ip}:${port}/sensor/getall`,
  });

  if (response) {
    if (response.status == 200)
      return res
        .status(200)
        .json({ data: response.data.data ? response.data.data : "success" });
    if (response.status == 400)
      return res.status(400).json({ error: response.data.error });
  }
  return res
    .status(400)
    .json({ error: "unknown error occured at API gateway" });
});

sensorRoute.delete("/sensor/delete/:id", async (req, res) => {
  let version = "1.0.0";

  const { ip, port } = await getService("sensor-service", version);

  let response = await callService({
    method: "delete",
    url: `http://${ip}:${port}/sensor/delete/${req.params.id}`,
    data: req.body,
  }).catch((err) => console.log(err));

  if (response) {
    if (response.status == 200)
      return res
        .status(200)
        .json({ data: response.data.data ? response.data.data : "success" });
    if (response.status == 400)
      return res.status(400).json({ error: response.data.error });
  }
  // }

  return res
    .status(400)
    .json({ error: "unknown error occured at service gateway" });
});

module.exports = sensorRoute;
