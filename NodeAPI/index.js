const express = require("express");
const mongoose = require('mongoose')
const { default: axios } = require("axios");

const sensor = require('./senser')
const app = express();


app.use(express.json());

app.use('/api/fireAlarmSystem',sensor)

//databse connection
mongoose.connect(process.env.MONGO_URL,{
    useNewUrlParser: true,
    useUnifiedTopology: true}) 
const db = mongoose.connection
db.on('error', error => console.error(error))
db.once('open', ()=> console.log('connected to mongoose')) 

const server = app.listen(5000,()=>{
    console.log("server is runnng....");
})

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