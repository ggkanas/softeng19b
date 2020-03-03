import React, { Component } from 'react';
import { UserContext } from './UserContext';
import {ErrorHandler} from './ErrorHandler';

/*const ProductionTypeEnum = Object.freeze({"AllTypes":0, "Fossil Gas":1, "Hydro Run-of-river and poundage":2, "Hydro Pumped Storage":3,
    "Hydro Water Reservoir":4, "Fossil Hard coal": 5, "Nuclear":6, "Fossil Brown coal/Lignite":7, "Fossil Oil":8,
    "Fossil Oil shale":9, "Biomass":10, "Fossil Peat":11, "Wind Onshore":12, "Other":13, "Wind Offshore":14,
    "Fossil Coal-derived gas":15, "Waste":16, "Solar":17, "Geothermal":18, "Other renewable":19, "Marine":20,
    "AC Link":21, "Transformer":22, "DC Link":23, "Substation":24
});*/

var keyCounter = 0;

const productionTypes = ["AllTypes", "Fossil Gas", "Hydro Run-of-river and poundage", "Hydro Pumped Storage",
    "Hydro Water Reservoir", "Fossil Hard coal", "Nuclear", "Fossil Brown coal/Lignite", "Fossil Oil",
    "Fossil Oil shale", "Biomass", "Fossil Peat", "Wind Onshore", "Other", "Wind Offshore",
    "Fossil Coal-derived gas", "Waste", "Solar", "Geothermal", "Other renewable", "Marine",
    "AC Link", "Transformer", "DC Link", "Substation"];

class ProductionTypeSelect extends Component {

    constructor(props) {
        super(props);
        this.productionTypeChange = this.productionTypeChange.bind(this);
    }

    productionTypeChange() {
        console.log("Production Type changed");
        this.props.setParentState({productionType:document.getElementById('productionTypeSel').value});
    }

    render() {
        console.log('ref to dataType ', this.props.dataType);
        //console.log('ref to areaName ', this.props.areaName);
        if (this.props.dataType === "AggregatedGenerationPerType" ) return (
            <div><label htmlFor="productionTypeSel">Select Production Type</label>
            <select className="form-control" id="productionTypeSel" onChange={this.productionTypeChange} name="productionTypeSelList" defaultValue="0">
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
            </select></div>
        );
        else return "";
    }

}

// Disable form submissions if there are invalid fields
(function() {
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

class FormSubmit extends Component {

    static contextType = UserContext;

    constructor(props) {
        super(props);
        this.state = {
            dataType: "ActualTotalLoad",
            productionType: productionTypes[0],
        }
        //console.log('first ref to dataType ', this.dataType.current);
        this.areaName = React.createRef();
        this.resolutionType = React.createRef();
        this.year = React.createRef();
        this.month = React.createRef();
        this.day = React.createRef();
        this.handleSubmit = this.handleSubmit.bind(this);
        this.dataTypeChange = this.dataTypeChange.bind(this);
    }

    handleSubmit(event) {
        //console.log("ref to dataType new ", this.dataType);
        let url = 'https://localhost:8765/energy/api/';
        url += this.state.dataType + '/';
        url += this.areaName.current.value + '/'
        if (this.state.dataType === "AggregatedGenerationPerType") url += productionTypes[this.state.productionType] + '/';
        url += this.resolutionType.current.value + '/';
        if (this.month.current.value == null) {
            url += 'year/' + this.year.current.value;
        }
        else if(this.day.current.value == null) {
            url += 'month/' + this.year.current.value + '-' + this.month.current.value.toString().padStart(2, "0");
        }
        else {
            url += 'date/' + this.year.current.value + '-' + this.month.current.value.toString().padStart(2, "0") + '-';
            url += this.day.current.value.toString().padStart(2, "0");
        }
        fetch(url,{
            method: 'GET',
            headers: {
                'X-OBSERVATORY-AUTH': this.context.token,
                'Content-Type':'application/x-www-form-urlencoded',
            },
        }).then((response) => {if(!response.ok) throw Error(response.status); else return response.json();})
        .then(json => this.props.doDisplay(json, 0)).catch((error) => {console.log("Error ", error); ErrorHandler.handleErrors(error);});;
    }

    dataTypeChange() {
        console.log("Data Type changed");
        this.setState({dataType: document.getElementById('dataTypeSel').value});
    }

    render () {
        return (
             <div><form onSubmit={this.handleSubmit}  className="needs-validation" noValidate>
                <div className="form-group">
                    <label htmlFor="dataTypeSel">Select Data Type</label>
                    <select className="form-control" id="dataTypeSel" name="dataTypeSelList" onChange={this.dataTypeChange} defaultValue="ActualTotalLoad">
                        <option value="ActualTotalLoad">Actual Total Load</option>
                        <option value="AggregatedGenerationPerType">Aggregated Generation Per Type</option>
                        <option value="DayAheadTotalLoadForecast">Day-Ahead Total Load Forecast</option>
                        <option value="ActualvsForecast">Actual Total Load vs Day-Ahead Total Load Forecast</option>
                    </select>
                    <label htmlFor="areaNameIn">Select Area</label>
                    <input type="text" className="form-control" id="areaNameIn" required ref={this.areaName} />
                    <div className="invalid-feedback">Please fill out this field.</div>
                    <ProductionTypeSelect dataType={this.state.dataType} setParentState={(state) => this.setState(state)} productionType={this.state.productionType} />
                    <label htmlFor="resolutionTypeSel">Select Resolution Type</label>
                    <select className="form-control" id="resolutionTypeSel" name="resolutionTypeSelList" defaultValue="PT15M" ref={this.resolutionType}>
                        <option value="PT15M">PT15M</option>
                        <option value="PT30M">PT30M</option>
                        <option value="PT60M">PT60M</option>
                    </select>
                    <label htmlFor="yearInput">Select Year</label>
                    <input type="number" className="form-control" id="yearInput" name="yearInput" min="1900" max="2200" ref={this.year} required />
                    <div className="invalid-feedback">Please fill out this field.</div>
                    <label htmlFor="monthInput">Select Month</label>
                    <input type="number" className="form-control" id="monthInput" name="monthInput" min="1" max="12" ref={this.month}/>
                    <label htmlFor="dayInput">Select Day</label>
                    <input type="number" className="form-control" id="dayInput" name="dayInput" min="1" max="31" ref={this.day}/>
                    <button className="btn btn-primary" type="submit">
                        Submit
                    </button>
                </div>
             </form>

             <script>

            </script></div>
         );
    }
}

class MapSubmit extends Component {
    render () {
        return (<div>Not Implemented Yet.</div>);
    }
}

class TableDisplay extends Component {



    renderTH(i) {
        return (<th key={keyCounter++}>{i}</th>);
    }

    renderTD(i) {
        return (<td key={keyCounter++}>{i}</td>);
    }

    renderTHEAD(arr) {
        var res = [];
        for (var i = 0; i < arr.length; ++i) {
            res.push(this.renderTH(arr[i]));
        }
        return res;
    }

    renderTR(arr) {
        var res = [];
        for (var i = 0; i < arr.length; ++i) {
            res.push(this.renderTD(arr[i]));
        }
        return <tr key={keyCounter++}>{res}</tr>;
    }

    renderTRs(arr) {
        var res = [];
        for (var i = 0; i < arr.length; ++i) {
            res.push(this.renderTR(arr[i]));
        }
        return res;
    }

    render () {
        return (
            <table className="table table-striped table-bordered">
                <thead className="table-primary">
                    <tr>
                        {this.renderTHEAD(this.props.headContents)}
                    </tr>
                </thead>
                <tbody>
                    {this.renderTRs(this.props.tableContents)}
                </tbody>
            </table>
        );
    }
}

class DiagramDisplay extends Component {
    render () {
        return (<div>Not Implemented Yet.</div>);
    }
}

class Submit extends Component {
    render() {
        if (this.props.inmode === 'map') return <MapSubmit />;
        else return <FormSubmit doDisplay = {(json, err) => this.props.doDisplay(json, err)} />;
    }
}

class Display extends Component {
    render () {
        if (this.props.outmode === 'diagram') return <DiagramDisplay />;
        else return <TableDisplay
            headContents={this.props.parentState.headContents}
            tableContents={this.props.parentState.tableContents}
            />;
    }
}

export class Content extends Component {

    constructor(props) {
        super(props);
        this.state = {
                headContents: ["Table"],
                tableContents: [["Empty"]],
        }
        this.doDisplay = this.doDisplay.bind(this);
    }

    doDisplay(json, err) {
        var headContent = ["Source", "DataSet", "AreaName", "AreaTypeCode", "MapCode", "ResolutionCode", "Year", "Month"];
        var tableContent = [];
        var flags = [false, false, false, false, false, false, false];
        if(err || json == null) {
                json = ([ {
                    "Source" : "entso-e",
                    "DataSet" : "ActualTotalLoad",
                    "AreaName" : "Greece",
                    "AreaTypeCode" : "CTY",
                    "MapCode" : "GR",
                    "ResolutionCode" : "PT60M",
                    "Year" : "2018",
                    "Month" : "1",
                    "Day" : "1",
                    "DateTimeUTC" : "2018-01-01 01:00:00.0000000",
                    "ActualTotalLoadValue" : "4767.82",
                    "UpdateTimeUTC" : "2018-09-04 11:16:37.0000000"
                },
                {
                    "Source" : "entso-e",
                    "DataSet" : "ActualTotalLoad",
                    "AreaName" : "Greece",
                    "AreaTypeCode" : "CTY",
                    "MapCode" : "GR",
                    "ResolutionCode" : "PT60M",
                    "Year" : "2018",
                    "Month" : "1",
                    "Day" : "1",
                    "DateTimeUTC" : "2018-01-01 02:00:00.0000000",
                    "ActualTotalLoadValue" : "4509.63",
                    "UpdateTimeUTC" : "2018-09-04 11:16:37.0000000"
                },
                {
                    "Source" : "entso-e",
                    "DataSet" : "ActualTotalLoad",
                    "AreaName" : "Greece",
                    "AreaTypeCode" : "CTY",
                    "MapCode" : "GR",
                    "ResolutionCode" : "PT60M",
                    "Year" : "2018",
                    "Month" : "1",
                    "Day" : "1",
                    "DateTimeUTC" : "2018-01-01 03:00:00.0000000",
                    "ActualTotalLoadValue" : "4445.26",
                    "UpdateTimeUTC" : "2018-09-04 11:16:37.0000000"
                } ]);
        }
        if (json[0].Day != null) {
            headContent.push("Day");
            flags[0] = true;
        }
        if (json[0].DateTimeUTC != null) {
            headContent.push("DateTimeUTC");
            flags[1] = true;
        }
        if (json[0].ProductionType != null) {
            headContent.push("ProductionType");
            flags[2] = true;
        }
        if (json[0].DayAheadTotalLoadForecastValue != null) {
            headContent.push("DayAheadTotalLoadForecastValue");
            flags[3] = true;
        }
        if (json[0].ActualTotalLoadValue != null) {
            headContent.push("ActualTotalLoadValue");
            flags[4] = true;
        }
        if (json[0].ActualGenerationOutputValue != null) {
            headContent.push("ActualGenerationOutputValue");
            flags[5] = true;
        }
        if (json[0].UpdateTimeUTC != null) {
            headContent.push("UpdateTimeUTC");
            flags[6] = true;
        }
        console.log("json length is ", json.length);
        for (var i = 0; i < json.length; ++i) {
            tableContent.push([]);
            tableContent[i].push(json[i].Source);
            tableContent[i].push(json[i].Dataset);
            tableContent[i].push(json[i].AreaName);
            tableContent[i].push(json[i].AreaTypeCode);
            tableContent[i].push(json[i].MapCode);
            tableContent[i].push(json[i].ResolutionCode);
            tableContent[i].push(json[i].Year);
            tableContent[i].push(json[i].Month);
            if (flags[0]) tableContent[i].push(json[i].Day);
            if (flags[1]) tableContent[i].push(json[i].DateTimeUTC);
            if (flags[2]) tableContent[i].push(json[i].ProductionType);
            if (flags[3]) tableContent[i].push(json[i].DayAheadTotalLoadForecastValue);
            if (flags[4]) tableContent[i].push(json[i].ActualTotalLoadValue);
            if (flags[5]) tableContent[i].push(json[i].ActualGenerationOutputValue);
            if (flags[6]) tableContent[i].push(json[i].UpdateTimeUTC);
        }
        console.log(tableContent);
        this.setState({headContents: headContent, tableContents: tableContent});

    }

    render () {
        return (
            <div>
            <Submit inmode={this.props.inmode} doDisplay={(json, err) => this.doDisplay(json, err)} />
            <Display outmode={this.props.outmode} parentState={this.state} />
            </div>
        );
    }
}
