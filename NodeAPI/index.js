const express = require("express");
const mongoose = require('mongoose')

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

app.listen(5000,()=>{
    console.log("server is runnng....");
})