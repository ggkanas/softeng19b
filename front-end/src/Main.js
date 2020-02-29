import React, { Component } from 'react';
import { Content } from './Content.js';

class Main extends Component {

    constructor(props) {
        super(props);
        this.state = {
            inmode : "form",
            outmode: "table",
        }
        //this.inmode = React.createRef();
        //this.outmode = React.createRef();
    }

    render() {
        return (
            <div>
                <div className='row'>
                    <h1>
                        Main page
                    </h1>
                </div>
                <div className="row">
                    <form>
                        <label htmlFor="inmode">Submit Mode</label><br/>
                        <input id="inform" type="radio" name='inmode' value='form' defaultChecked onClick={()=>this.setState({inmode: "form"})} />
                        <label htmlFor="inform">Form</label><br/>
                        <input id="inmap" type="radio" name='inmode' value='map' onClick={()=>this.setState({inmode: "map"})} />
                        <label htmlFor="inmap">Map</label><br/>

                        <label htmlFor="outmode">Display Mode</label><br/>
                        <input id="outtable" type="radio" name='outmode' value='table' defaultChecked onClick={()=>this.setState({outmode: "table"})} />
                        <label htmlFor="outtable">Table</label><br/>
                        <input id="outdiagram" type="radio" name='outmode' value='diagram' onClick={()=>this.setState({outmode: "diagram"})} />
                        <label htmlFor="outdiagram">Diagram</label>
                    </form>
                </div>
                <div className="row">
                    <Content inmode = {this.state.inmode} outmode = {this.state.outmode}/>
                </div>
            </div>
        );
    }

}

export default Main;
