const express = require("express");
const ServiceRegistry = require("./lib/serviceRegistry");
const service = express();
const cors = require("cors");

module.exports = (config) => {
  const log = config.log();
  const serviceRegistry = new ServiceRegistry(log);
  // Add a request logging middleware in development mode
  if (service.get("env") === "development") {
    service.use((req, res, next) => {
      log.debug(`${req.method}: ${req.url}`);
      return next();
    });
  }

  // register service api
  service.put("/register/:name/:version/:port", (req, res) => {
    const { name, version, port } = req.params;

    console.log(name);
    console.log(version);
    console.log(port);

    const ip = req.connection.remoteAddress.includes("::")
      ? `[${req.connection.remoteAddress}]`
      : req.connection.remoteAddress;

    console.log(ip);

    const serviceKey = serviceRegistry.register(name, version, ip, port);

    console.log(serviceKey);

    return res.json({ result: serviceKey });
  });

  service.delete("/unregister/:name/:version/:port", (req, res) => {
    const { name, version, port } = req.params;

    console.log(name);
    console.log(version);
    console.log(port);

    const ip = req.connection.remoteAddress.includes("::")
      ? `[${req.connection.remoteAddress}]`
      : req.connection.remoteAddress;

    console.log(ip);

    const serviceKey = serviceRegistry.unregister(name, version, ip, port);

    console.log(serviceKey);

    return res.json({ result: serviceKey });
  });

  service.get("/find/:name/:version", (req, res) => {
    const { name, version } = req.params;

    const svc = serviceRegistry.get(name, version);

    if (!svc) return res.status(400).json({ result: "service not found" });
    return res.json({ result: svc });
  });

  // eslint-disable-next-line no-unused-vars

  service.use(cors({ origin: "*" }));
  service.use((error, req, res, next) => {
    res.status(error.status || 500);
    // Log out the error to the console
    log.error(error);
    return res.json({
      error: {
        message: error.message,
      },
    });
  });
  return service;
};
