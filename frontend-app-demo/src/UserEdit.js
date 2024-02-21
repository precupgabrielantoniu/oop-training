import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import { withRouter } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import AppNavbar from './AppNavbar';

class UserEdit extends Component{
    emptyAddress = {
        number: 0,
        street: '',
        city: '',
        county: ''
    };
    emptyItem = {
        name: '',
        email: '',
        password: '',
        address: this.emptyAddress
    };

    constructor(props) {
        super(props);
        this.state = {
            itemAddress: this.emptyAddress,
            item: this.emptyItem
        };
        this.handleUserChange = this.handleUserChange.bind(this);
        this.handleAddressChange = this.handleAddressChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    async componentDidMount() {
        if (this.props.match.params.id !== 'new') {
            const user = await (await fetch(`/demo/${this.props.match.params.id}`)).json();
            this.setState({item: user, itemAddress: user.address});

        }
    }

    handleUserChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let item = {...this.state.item};
        item[name] = value;
        this.setState({item});
    }

    handleAddressChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let itemAddress = {...this.state.itemAddress};
        let item = {...this.state.item};
        itemAddress[name] = value;
        item.address[name] = value;
        this.setState({item, itemAddress});
    }

    async handleSubmit(event) {
        event.preventDefault();
        const {item, itemAddress} = this.state;
        console.log("The user id is " + item.id);
        console.log(JSON.stringify(item));
        console.log(JSON.stringify(itemAddress));
        await fetch('/demo' + (item.id ? '/user/' + item.id : '/add/request-body'), {
            method: (item.id) ? 'PUT' : 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(item),
        });
        this.props.history.push('/users');
    }

    render() {
        const {item, itemAddress} = this.state;
        const title = <h2>{item.id ? 'Edit User' : 'Add User'}</h2>;

        return <div>
            <AppNavbar/>
            <Container>
                {title}
                <Form onSubmit={this.handleSubmit}>
                    <FormGroup>
                        <Label for="name">Name</Label>
                        <Input type="text" name="name" id="name" value={item.name || ''}
                               onChange={this.handleUserChange} autoComplete="name"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="email">Email</Label>
                        <Input type="text" name="email" id="email" value={item.email || ''}
                               onChange={this.handleUserChange} autoComplete="email"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="password">Password</Label>
                        <Input type="text" name="password" id="password" value={item.password || ''}
                               onChange={this.handleUserChange} autoComplete="password"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="number">Address Number</Label>
                        <Input type="text" name="number" id="number" value={itemAddress.number || ''}
                               onChange={this.handleAddressChange} autoComplete="number"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="street">Address Street</Label>
                        <Input type="text" name="street" id="street" value={itemAddress.street || ''}
                               onChange={this.handleAddressChange} autoComplete="street"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="city">Address City</Label>
                        <Input type="text" name="city" id="city" value={itemAddress.city || ''}
                               onChange={this.handleAddressChange} autoComplete="city"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="county">Address County</Label>
                        <Input type="text" name="county" id="county" value={itemAddress.county || ''}
                               onChange={this.handleAddressChange} autoComplete="county"/>
                    </FormGroup>
                    <FormGroup>
                        <Button color="primary" type="submit">Save</Button>{' '}
                        <Button color="secondary" tag={Link} to="/users">Cancel</Button>
                    </FormGroup>
                </Form>
            </Container>
        </div>
    }
}
export default withRouter(UserEdit);