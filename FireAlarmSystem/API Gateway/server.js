// Staff Server.js

// imports
const express = require("express");
const app = express();

require("dotenv").config();
const PORT = process.env.SERVICEPORT;
const cors = require("cors");
// const fileupload = require("express-fileupload");
// const mongoose = require("mongoose");

// const aws = require("aws-sdk");
// const multer = require("multer");
// const multerS3 = require("multer-s3");

// // employee route
// const staffRoute = require("./Routes/staff.route");
// const clientRoute = require("./Routes/client.route");
// const packageRoute = require("./Routes/package.route");
// const serviceRoute = require("./Routes/service.route");
// const foodRoute = require("./Routes/food.route");
// const orderRoute = require("./Routes/order.route");
// const mealRoute = require("./Routes/meal.route");
// const employeeRoute = require("./Routes/employee.route");
// const authRoute = require("./Routes/auth.route");
// const ruleRoute = require("./Routes/rule.route");
// const shiftRoute = require("./Routes/shift.route");
const deviceRoute = require("./Routes/device.route");
// mongoose.Promise = global.Promise;
// mongoose
//   .connect(process.env.DB, {
//     useNewUrlParser: true,
//     useUnifiedTopology: true,
//   })
//   .then(
//     () => {
//       console.log("Database is connected");
//     },
//     (err) => {
//       console.log("Can not connect to the database" + err);
//     }
//   );

// middlewares configurations
app.use(cors({ origin: "*" }));
app.use(express.urlencoded({ extended: true }));
app.use(express.json());
// app.use(
//   fileupload({
//     useTempFiles: true,
//   })
// );

// connect route
// app.use("/auth", authRoute);
// app.use("/staff", staffRoute);
// app.use("/food", foodRoute);
// app.use("/client", clientRoute);
// app.use("/package", packageRoute);
// app.use("/service", serviceRoute);
// app.use("/meal", mealRoute);
// app.use("/employee", employeeRoute);
// app.use("/order", orderRoute);
// app.use("/rule", ruleRoute);
// app.use("/shift", shiftRoute);
app.use("/sensor", deviceRoute);

// app.use("/upload", uploadRoute);

// uploader

// aws.config.update({
//   accessKeyId: process.env.SPACES_ACCESS_KEY,
//   secretAccessKey: process.env.SPACES_SECRET_KEY,
// });

// const spacesEndpoint = new aws.Endpoint("sgp1.digitaloceanspaces.com");
// const s3 = new aws.S3({
//   endpoint: spacesEndpoint,
// });

// const upload = multer({
//   fileFilter: (req, file, cb) => {
//     if (file.mimetype === "image/jpeg" || file.mimetype === "image/png") {
//       cb(null, true);
//     } else {
//       cb(new multer.MulterError("Please upload a JPEG file"));
//     }
//   },
//   storage: multerS3({
//     s3,
//     bucket: "mealchieffilespace",
//     acl: "public-read",
//     // eslint-disable-next-line
//     key: function (req, file, cb) {
//       cb(null, `${Date.now().toString()}${file.originalname}`);
//     },
//     contentType: function (req, file, cb) {
//       cb(null, `image/jpeg`);
//     },
//   }),
// }).single("file");

// app.post("/upload", upload, (req, res, next) => {
//   console.log(req);

//   if (!req.file) {
//     res.status(400).json({
//       message: "Please upload a file",
//     });
//   } else {
//     try {
//       upload(req, res, (err) => {
//         if (err) {
//           console.log(err);
//           let error;
//           if (err.name === "MulterError") {
//             error = err.code;
//           } else {
//             error = err;
//           }
//           return res.status(400).json({ success: false, error: error });
//         }
//         const url = req.file.location;

//         console.log(
//           // `${url.split("digitaloceanspaces.com")[0]}cdn.digitaloceanspaces.com${
//           //   url.split("digitaloceanspaces.com")[1]

//           url
//           // }`
//         );

//         return res.status(200).json({ data: url });
//       });
//     } catch (err) {
//       console.log("==========Uploading Error===========");
//       console.log(err);
//       console.log("====================================");
//     }
//   }
// });

const server = app.listen(PORT, function () {
  console.log("API Gateway running on Port:", PORT);
});

process.on("SIGTERM", () => {
  server.close(() => {
    console.log("Process terminated");
  });
});
