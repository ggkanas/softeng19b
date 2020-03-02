import React, { Component } from 'react';
import UserConfirmationModal from './UserConfirmationModal';
import './App.css';

const doubler = function(i) {
    return  i * 2;
}

function tripler(i) {
    return i * 3;
}

class Footer extends Component {

    constructor(props) {
        super(props);
        this.state = {
            modalVisible: false
        };

        this.showModal = this.showModal.bind(this);
        this.hideModal = this.hideModal.bind(this);
    }

    showModal() {
        console.log('show modal');
        this.setState({modalVisible:true});
    }

    hideModal(userChoice) {
        //handle user choice
        console.log(userChoice);
        this.setState({modalVisible:false});
    }

    render() {

        const list = [1, 2, 3];
        const ld = list.map(doubler);
        const lt = list.map(tripler);

        const renderedList = [];
        lt.forEach(i => {
            renderedList.push(<span key={i}>{i}</span>);
        });

        return (
            <div className='row'>
                <span className='right-align'>Team <strong>Four At Last</strong></span>
            </div>

        );
    }

}

export default Footer;
