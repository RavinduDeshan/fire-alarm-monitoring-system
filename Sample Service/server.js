// Staff Server.js

// imports
const express = require("express");
const app = express();
require("dotenv").config();
const PORT = process.env.SERVICEPORT;
const cors = require("cors");
const fileupload = require("express-fileupload");
const mongoose = require("mongoose");
const { default: axios } = require("axios");

// employee route
const clientRoute = require("./Routes/client.route");

// validate server client type

mongoose.Promise = global.Promise;
mongoose
  .connect(process.env.DB, {
    useNewUrlParser: true,
    useUnifiedTopology: true,
  })
  .then(
    () => {
      console.log("Database is connected");
    },
    (err) => {
      console.log("Can not connect to the database" + err);
    }
  );

// middlewares configurations
app.use(cors());
app.use(express.urlencoded({ extended: true }));
app.use(express.json());
app.use(
  fileupload({
    useTempFiles: true,
  })
);

// connect route
app.use("/client", clientRoute);

const server = app.listen(PORT, function () {
  console.log("Client Service Server is running on Port:", PORT);
});

// To do Try and catch the service registry un availability errors
server.on("listening", () => {
  const registerService = () =>
    axios
      .put(
        `${process.env.SERVICEREGISTRYURL}/${process.env.SERVICENAME}/${process.env.SERVICEVERSION}/${process.env.SERVICEPORT}`
      )
      .then((res) => {
        console.log(`Service Registered Key : ${res.data.result}`);
      })
      .catch((err) => {
        console.log(`Error on Registry : ${err}`);
      });

  registerService();
  const interval = setInterval(registerService, 20000);
});
