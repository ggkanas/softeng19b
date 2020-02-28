import React, { Component } from 'react';
import { UserContext } from './UserContext';

const ProductionTypeEnum = Object.freeze({"AllTypes":0, "Fossil Gas":1, "Hydro Run-of-river and poundage":2, "Hydro Pumped Storage":3,
    "Hydro Water Reservoir":4, "Fossil Hard coal": 5, "Nuclear":6, "Fossil Brown coal/Lignite":7, "Fossil Oil":8,
    "Fossil Oil shale":9, "Biomass":10, "Fossil Peat":11, "Wind Onshore":12, "Other":13, "Wind Offshore":14,
    "Fossil Coal-derived gas":15, "Waste":16, "Solar":17, "Geothermal":18, "Other renewable":19, "Marine", 20
    "AC Link":21, "Transformer":22, "DC Link":23, "Substation":24
})

class ProductionTypeSelect extends Component {
    render() {
        if (this.props.dataType.current.value === "AggregatedGenerationPerType") return (
            <label htmlFor="productionTypeSel">Select Production Type</label>
            <select className="form-control" id="productionTypeSel" name="productionTypeSelList" ref={this.props.productionType}>
                <option value="0">All Types</option>
                <option value="1">Fossil Gas</option>
                <option value="2">Hydro Run-of-river and poundage</option>
                <option value="3">Hydro Pumped Storage</option>
                <option value="4">Hydro Water Reservoir</option>
                <option value="5">Fossil Hard coal</option>
                <option value="6">Nuclear</option>
                <option value="7">Fossil Brown coal/Lignite</option>
                <option value="8">Fossil Oil</option>
                <option value="9">Fossil Oil shale</option>
                <option value="10">Biomass</option>
                <option value="11">Fossil Peat</option>
                <option value="12">Wind Onshore</option>
                <option value="13">Other</option>
                <option value="14">Wind Offshore</option>
                <option value="15">Fossil Coal-derived gas</option>
                <option value="16">Waste</option>
                <option value="17">Solar</option>
                <option value="18">Geothermal</option>
                <option value="19">Other renewable</option>
                <option value="20">Marine</option>
                <option value="21">AC Link</option>
                <option value="22">Transformerr</option>
                <option value="23">DC Link</option>
                <option value="24">Substation</option>
            </select>
        );
        else return;
    }

}

class FormSubmit extends Component {

    static contextType = UserContext;

    constructor(props) {
        super(props);
        this.dataType = React.createRef();
        this.areaName = React.createRef();
        this.productionType = React.createRef();
        this.resolutionType = React.createRef();
        this.year = React.createRef();
        this.month = React.createRef();
        this.day = React.createRef();
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit(event) {
        url = 'https://localhost:8765/energy/api/';
        url += this.dataType.current.value + '/';
        url += this.areaName.current.value + '/'
        if (this.daraType.current.value === "AggregatedGenerationPerType") url += productionTypes[this.productionType.current.value] + '/';
        url += this.resolutionType.current.value + '/';
        if (this.month.current.value == null) {
            url += 'year/' + this.year.current.value;
        }
        else if(this.day.current.value == null) {
            url += 'month/' + this.year.current.value + '-' + this.month.current.value;
        }
        else {
            url += 'date/' + this.year.current.value + '-' + this.month.current.value + '-';
            url += this.day.current.value;
        }
        fetch(url,{
            method: 'GET',
            headers: {
                'X-OBSERVATORY-AUTH': this.context.username,
                'Content-Type':'application/x-www-form-urlencoded',
            },
        }).then((response) => response.json())
        .then(json => this.props.doDisplay(json));
    }

    render () {
        return (
             <form className="needs-validation" novalidate>
                <div className="form-group">
                    <label htmlFor="dataTypeSel">Select Data Type</label>
                    <select className="form-control" id="dataTypeSel" name="dataTypeSelList" ref={this.dataType}>
                        <option value="ActualTotalLoad">Actual Total Load</option>
                        <option value="AggregatedGenerationPerType">Aggregated Generation Per Type</option>
                        <option value="DayAheadTotalLoadForecast">Day-Ahead Total Load Forecast</option>
                        <option value="ActualvsForecast">Actual Total Load vs Day-Ahead Total Load Forecast</option>
                    </select>
                    <label htmlFor="areaNameIn">Select Area</label>
                    <input type="text" className="form-control" id="areaNameIn" name="areaNameInput" ref={this.areaName} required />
                    <div class="invalid-feedback">Please fill out this field.</div>
                    <ProductionTypeSelect dataType={this.dataType} productionType={this.productionType} />
                    <label htmlFor="resolutionTypeSel">Select Resolution Type</label>
                    <select className="form-control" id="resolutionTypeSel" name="resolutionTypeSelList" ref={this.resolutionType}>
                        <option value="PT15M">PT15M</option>
                        <option value="PT30M">PT30M</option>
                        <option value="PT60M">PT60M</option>
                    </select>
                    <label htmlFor="yearInput">Select Year</label>
                    <input type="number" className="form-control" id="yearInput" name="yearInput" min="1900" max="2200" ref={this.year} required />
                    <div class="invalid-feedback">Please fill out this field.</div>
                    <label htmlFor="monthInput">Select Month</label>
                    <input type="number" className="form-control" id="monthInput" name="monthInput" min="1" max="12" ref={this.month}/>
                    <label htmlFor="dayInput">Select Day</label>
                    <input type="number" className="form-control" id="dayInput" name="dayInput" min="1" max="31" ref={this.day}/>
                </div>
             </form>

             <script>
                // Disable form submissions if there are invalid fields
                (function() {
                    'use strict';
                    window.addEventListener('load', function() {
                        // Get the forms we want to add validation styles to
                        var forms = document.getElementsByClassName('needs-validation');
                        // Loop over them and prevent submission
                        var validation = Array.prototype.filter.call(forms, function(form) {
                            form.addEventListener('submit', function(event) {
                                if (form.checkValidity() === false) {
                                    event.preventDefault();
                                    event.stopPropagation();
                                }
                                form.classList.add('was-validated');
                            }, false);
                        });
                    }, false);
                })();
            </script>
         );
    }
}

class MapSubmit extends Component {
    render () {
        return (Not Implemented Yet.);
    }
}

class TableDisplay extends Component {
    render () {
        return (Not Implemented Yet.);
    }
}

class DiagramDisplay extends Component {
    render () {
        return (Not Implemented Yet.);
    }
}

export class Content extends Component {

    doDisplay(json) {
        
    }

    render () {
        if (this.props.inmode === 'map') submit = "<MapSubmit />";
        else submit = "<FormSubmit doDisplay = {(json) => this.doDisplay(json)} />";
        if (this.props.outmode === 'diagram') display = "<DiagramDisplay />";
        else display = "<TableDisplay />";
        return submit + '\n' + display;
    }
}
