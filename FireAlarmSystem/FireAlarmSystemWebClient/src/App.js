import React from 'react';
import './App.css';

import {SensorDetails} from "./components/SensorDetails";
import {BrowserRouter,Route, Switch} from "react-router-dom";
import {Navigation} from "./components/Navigation";





function App() {
  return (
    <BrowserRouter>
      <div className="container">

      <div class="jumbotron">
          <h1 className="m-3 d-flex justify-content-center" >Fire Alarm Monitoring System</h1>
          
          </div>
          
          <Navigation/>
           <Switch>
               
               <Route path = '/' component={SensorDetails}  />
               
           </Switch>
      </div>
    </BrowserRouter>
  );
}

export default App;
