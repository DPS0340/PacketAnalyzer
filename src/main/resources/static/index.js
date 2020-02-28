'use strict';

const element = React.createElement;


class Index extends React.Component {
    constructor(props) {
        super(props);
        this.state = {apiResponse: ""};
    }

    callAPI() {
        fetch("http://localhost:8888/testapi")
            .then(res => res.text())
            .then(res => this.setState({apiResponse: res}));
    }

    componentDidMount() {
        this.callAPI();
    }

    render() {
        return <h1>Result is: {this.state.apiResponse}</h1>;
    }
}

ReactDOM.render(<Index />, document.getElementById("main"));