import logo from "./logo.svg";
import {Component} from "react";
import Home from './Home';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import UserList from './UserList';
import UserEdit from "./UserEdit";
class App extends Component {
  state = {
    users: []
  };

  async componentDidMount() {
    const response = await fetch('/demo/all');
    const body = await response.json();
    this.setState({users: body});
  }

  render() {
    return (
        <Router>
          <Switch>
            <Route path='/' exact={true} component={Home}/>
            <Route path='/users' exact={true} component={UserList}/>
            <Route path='/users/:id' component={UserEdit}/>
          </Switch>
        </Router>
    );
  }
}
export default App;