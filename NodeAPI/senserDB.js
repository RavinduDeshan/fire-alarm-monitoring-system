const mongoose = require('mongoose')

const sensor = new mongoose.Schema({
    sensor_id:{
        type:Number,
        unique:true
    },
    location_floorNo:{
        type:Number
    },
    location_roomNo:{
        type:Number
    },
    co2_level:{
        type:Number
    },
    smoke_level:{
        type:Number
    },
    status:{
        type:String
    }
})
module.exports = mongoose.model('sensor',sensor)