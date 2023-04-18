// employee.model.js

const mongoose = require("mongoose");
const Schema = mongoose.Schema;

// Employee Schema
let client = new Schema(
  {
    clientId: {
      required: true,

      type: String,
    },

    companyName: {
      required: true,
      unique: true,
      type: String,
    },

    address: {
      required: true,

      type: String,
    },

    contactNo: {
      required: true,

      type: Number,
    },

    ClientAPIKey: {
      required: true,

      type: String,
    },

    SubscriptionPackageId: {
      type: String,
    },

    version: {
      type: String,
    },
    status: {
      type: Number,
    },
  },
  {
    collection: "client",
  }
);

module.exports = mongoose.model("client", client);
