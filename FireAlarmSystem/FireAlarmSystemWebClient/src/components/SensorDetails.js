import React , {Component} from 'react';
import {Table} from "react-bootstrap";

export class  SensorDetails extends Component{
    intervalID;

    constructor(props){
         super(props);
         this.state={sens:[]}
         

    }
    
    componentDidMount(){
        this.refreshList();
        
    }

    componentWillUnmount(){
        clearTimeout(this.intervalID);
    }
    
//get Sensor List
    refreshList(){
        fetch('http://localhost:8080/api/fireAlarmSystem/sensors')
        .then(response=> response.json())
        .then(data => {
            this.setState({sens:data}); 
//Re load data after 40seconds
            this.intervalID = setTimeout(this.refreshList.bind(this), 40000);
        })



        
    }
        
    





    


    render(){

        const {sens} =this.state;
       

        return(
            
         <div className="content">

            
             <div class="row">

        
                {this.state.sens.map(sensor=>(






<div class="col-md-4 col-xl-4">
    <div class={sensor.co2_level > 5 || sensor.smoke_level > 5 ? "card bg-c-pink order-card" : sensor.status == "active" ? "card bg-c-green order-card":"card bg-c-gray order-card"}>
        <div class="card-block">
        <i class="fas fa-broadcast-tower"></i>
            <h1 class="m-b-20">Sensor ID :   <b> <span class="f-right">{sensor.id}</span></b></h1><br/>
            <h2 class="text-right"><i class="fas fa-smog f-left"><span>  Smoke Level</span></i><span>{sensor.smoke_level}</span></h2>
            <h2 class="text-right"><i class="fas fa-atom f-left"><span>    CO2 Level</span></i><span>{sensor.co2_level}</span></h2><br/>
            <h4 class="m-b-0">Floor No: <span class="f-right">{sensor.location_floorNo}</span></h4>
            <h4 class="m-b-0">Room No: <span class="f-right">{sensor.location_roomNo}</span></h4>
        </div>
    </div>
</div>













               
                
              ))}






        

         </div>
         
         </div>
     

        )

    }
}