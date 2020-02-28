import React, { Component } from 'react';

class Main extends Component {

    constructor(props) {
        super(props);
        this.inmode = React.createRef();
        this.outmode = React.createRef();
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
                        <label htmlFor="inmode">Input Mode</label>
                        <input id="inform" type="radio" name='inmode' value='form' default ref={this.inmode} />
                        <label htmlFor="inform">Form</label>
                        <input id="inmap" type="radio" name='inmode' value='map' ref={this.inmode} />
                        <label htmlFor="inmap">Map</label>

                        <label htmlFor="outmode">Output Mode</label>
                        <input id="outtable" type="radio" name='outmode' value='table' default ref={this.outmode} />
                        <label htmlFor="outtable">Table</label>
                        <input id="outdiagram" type="radio" name='outmode' value='diagram' ref={this.outmode} />
                        <label htmlFor="outdiagram">Diagram</label>
                    </form>
                    <Content inmode = {this.state.inmode} outmode = {this.state.outmode}/> // <-------------------------------!!!
                </div>
            </div>
        );
    }

}

export default Main;
