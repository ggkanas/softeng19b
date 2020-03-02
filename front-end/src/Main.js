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
                    <Content inmode = {this.state.inmode} outmode = {this.state.outmode}/>
                </div>
            </div>
        );
    }

}

export default Main;
