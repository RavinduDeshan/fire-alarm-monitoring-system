"use strict";
require("dotenv").config();
const { default: axios } = require("axios");

let callService = async (requestOption) => {
  let response = await axios(requestOption)
    .then((res) => {
      console.log("success");
      return res;
    })
    .catch((err) => {
      console.log("========= service endpoint failure ===============");
      console.log(err);
      console.log("====================================");

      return err.response;
    });

  if (response) return response;
  return false;
};

async function getService(name, version) {
  const res = await axios
    .get(`${process.env.SERVICEREGISTRYURL}/find/${name}/${version}`)
    .catch((err) => {
      console.log("=========Error getting service===============");
      console.log(err);
      console.log("====================================");
    });

  console.log("========= service Response from registry ===============");
  console.log(res.data.result);
  console.log("====================================");

  return res.data.result;
}

module.exports = {
  callService,
  getService,
};
