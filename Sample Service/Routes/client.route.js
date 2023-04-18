"use strict";
const express = require("express");
const clientRoute = express.Router();
const bcrypt = require("bcrypt");
require("dotenv").config();

let client = require("../Models/client.model");

clientRoute.post("/add", async (req, res) => {
  let {
    clientId,
    companyName,
    address,
    contactNo,
    ClientAPIKey,

    SubscriptionPackageId,
    status,
    version,
  } = req.body;

  if (
    clientId == "" ||
    companyName == "" ||
    address == "" ||
    contactNo == "" ||
    ClientAPIKey == "" ||
    SubscriptionPackageId == "" ||
    version == "" ||
    status == ""
  )
    return res
      .status(200)
      .json({ warn: "Important field(s) are empty", code: 5001 });

  // clientId validation
  const clientIdObj = await client.findOne({
    clientId: clientId,
  });

  if (clientIdObj) {
    return res.status(200).json({
      warn: "This client Id  is already given.",
      code: 5000,
    });
  }

  // status validation
  if (!status) {
    status = 0;
  }

  // hash APISecreeat
  const salt = await bcrypt.genSalt();
  ClientAPIKey = await bcrypt.hash(ClientAPIKey, salt);

  const newConfig = new client({
    clientId,
    companyName,
    address,
    contactNo,
    ClientAPIKey,

    SubscriptionPackageId,
    status,
    version,
  });

  await newConfig
    .save()
    .then(async (respond) => {
      res.status(200).json({ data: "Successfull" });
    })
    .catch((err) => {
      res.status(400).json({ error: "Error on Saving new Client Data!" });
      console.log("Error on Saving  Client Data:", err);
    });
});

clientRoute.post("/update/:id", async (req, res) => {
  console.log(req.body);

  // hask keys
  // const salt = await bcrypt.genSalt();
  // let ClientAPIKeyTemp = await bcrypt.hash(eq.body.ClientAPIKey, salt);
  // let SecretKeyTemp = await bcrypt.hash(eq.body.SecretKey, salt);

  //   find by documentId
  client
    .findById(req.params.id)
    .then((clientDocument) => {
      clientDocument.clientId = req.body.clientId;
      clientDocument.companyName = req.body.address;
      clientDocument.status = req.body.status;
      clientDocument.address = req.body.address;
      clientDocument.contactNo = req.body.contactNo;
      clientDocument.version = req.body.version;
      // clientDocument.ClientAPIKey = ClientAPIKeyTemp;
      // clientDocument.SecretKey = SecretKeyTemp;
      clientDocument.SubscriptionPackageId = req.body.SubscriptionPackageId;

      //   log client
      console.log(clientDocument);

      clientDocument
        .save()
        .then(() => res.json("update client"))
        .catch((err) => res.status(400).json({ error: err }));
    })
    .catch((err) =>
      res.status(400).json({ error: "error on updating c;ient" })
    );
});

// update client api  Key only
clientRoute.post("/update-api-key/:id", async (req, res) => {
  console.log(req.body);

  // hashing
  const salt = await bcrypt.genSalt();
  let ClientAPIKeyTemp = await bcrypt.hash(eq.body.ClientAPIKey, salt);

  //   find by documentId
  client
    .findById(req.params.id)
    .then((clientDocument) => {
      clientDocument.ClientAPIKey = ClientAPIKeyTemp;

      //   log client
      console.log(clientDocument);

      clientDocument
        .save()
        .then(() => res.json("update client"))
        .catch((err) => res.status(400).json("client update Error : " + err));
    })
    .catch((err) => res.status(400).json("client update Error : " + err));
});

// update client Secret Key only
clientRoute.post("/update-secret-key/:id", async (req, res) => {
  console.log(req.body);

  // hashing
  const salt = await bcrypt.genSalt();
  let SecretKeyTemp = await bcrypt.hash(eq.body.SecretKey, salt);

  //   find by documentId
  client
    .findById(req.params.id)
    .then((clientDocument) => {
      clientDocument.ClientAPIKey = SecretKeyTemp;

      //   log client
      console.log(clientDocument);

      clientDocument
        .save()
        .then(() => res.json("update client"))
        .catch((err) => res.status(400).json("client update Error : " + err));
    })
    .catch((err) => res.status(400).json("client update Error : " + err));
});

// deactivate client
clientRoute.post("/deactivate/:id", async (req, res) => {
  console.log(req.body);

  //   find by documentId
  client
    .findById(req.params.id)
    .then((clientDocument) => {
      clientDocument.status = 0;

      //   log client
      console.log(clientDocument);

      clientDocument
        .save()
        .then(() => res.json("update client"))
        .catch((err) =>
          res.status(400).json({ error: "Error on deactiviating client!" })
        );
    })
    .catch((err) =>
      res.status(400).json({ error: "Error on finding client!" })
    );
});

// activate api
clientRoute.post("/activate/:id", async (req, res) => {
  console.log(req.body);

  //   find by documentId
  client
    .findById(req.params.id)
    .then((clientDocument) => {
      clientDocument.status = 1;

      //   log client
      console.log(clientDocument);

      clientDocument
        .save()
        .then(() => res.json("update client"))
        .catch((err) =>
          res.status(400).json({ error: "Error on activiating client!" })
        );
    })
    .catch((err) =>
      res.status(400).json({ error: "Error on finding client data!" })
    );
});

// update package only
clientRoute.post("/update-db/:id", async (req, res) => {
  console.log(req.body);

  //   find by documentId
  client
    .findById(req.params.id)
    .then((clientDocument) => {
      clientDocument.SubscriptionPackageId = req.body.SubscriptionPackageId;

      clientDocument
        .save()
        .then(() => res.json("update client"))
        .catch((err) => res.status(400).json("client update Error : " + err));
    })
    .catch((err) =>
      res.status(400).json({ error: "Error on updating client data!" })
    );
});

// get client by document ID
clientRoute.route("/:id").get(function (req, res) {
  let id = req.params.id;
  client.findOne({ _id: id }, function (err, client) {
    if (err) {
      console.log(err);
    } else {
      res.status(200).json({ success: true, data: client });
    }
  });
});

// get client by client ID
clientRoute.route("/clientId/:id").get(function (req, res) {
  let id = req.params.id;
  client.findOne({ clientId: id }, function (err, client) {
    if (err) {
      console.log(err);
    } else {
      res.status(200).json({ success: true, data: client });
    }
  });
});

// remove client by documentId
clientRoute.delete("/delete/:id", async (req, res) => {
  console.log("id is", req.params.id);

  await client
    .findOneAndDelete({ _id: req.params.id })
    .then((client) => res.json({ data: client }))
    .catch((err) => {
      res.status(400).json("Delete client occur an Error: " + err);

      console.log("Delete client occur an Error", err);
    });
});

// remove client by client ID
clientRoute.delete("/delete-by-client-id/:id", async (req, res) => {
  console.log("id is", req.params.id);

  await client
    .findOneAndDelete({ clientId: req.params.id })
    .then((client) => res.json({ data: client }))
    .catch((err) => {
      res.status(400).json("Delete client occur an Error: " + err);

      console.log("Delete client occur an Error", err);
    });
});

// get all clients Data
clientRoute.get("/", async (req, res) => {
  await client
    .find(function (err, clients) {
      if (err) {
        console.log(err);
      } else {
        res.status(200).json({ success: true, data: clients });
      }
    })
    .clone()
    .sort({ updatedAt: 1 });
});

module.exports = clientRoute;
