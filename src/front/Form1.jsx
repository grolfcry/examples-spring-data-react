import axios from 'axios'
import React, {Component} from 'react';
import {Button, Checkbox, Col, ControlLabel, FormControl, FormGroup, Grid, Label, Panel, Row} from 'react-bootstrap';

const style = `
.word-wrap{
  word-break: break-all;
}
`;
export default class Form1 extends Component {
    constructor(props) {
        super(props);
        this.state = {
            username: '',
            password: '',
            email: '',
            fullname: '',
            resultState:'',
            resultStateResponse:''
        };
        const username = localStorage.getItem("username")
        if (username) {
            this.state.username = username;
        }
        const email = localStorage.getItem("email")
        if (email) {
            this.state.email = email;
        }
         const fullname = localStorage.getItem("fullname")
         if (fullname) {
             this.state.fullname = fullname;
         }

    }


    validateFormSettings() {
        return this.state.username.length > 0 && this.state.password.length > 0 && this.state.fullname.length>0 && this.state.email.length>0
    }

    handleChange = (event) => {
        this.setState({
            [event.target.id]: event.target.type === 'checkbox' ? event.target.checked : event.target.value,
        });
    }

    store() {
        localStorage.setItem("username", this.state.username)
        localStorage.setItem("fullname", this.state.fullname)
        localStorage.setItem("email", this.state.email)

    }


    handleSubmit = (event) => {
        this.setState({resultState: ''})
        this.setState({resultStateResponse: ''})
        this.store()
        axios.post('/sendform/', JSON.stringify(this.state, (key, value) => {
            return key == 'password' ? value : value // set password to null?
        }), {
            withCredentials: true,
            auth: {
                username: this.state.username,
                password: this.state.password
            },
            headers: {
                               'Content-Type': 'application/json'
                               }
        })
            .then(response => {
                    this.setState({
                        epicKey: response.data.key,
                        resultState: "Успешно"
                    });
                    console.debug("Успешно")
                }
            ).catch(reason => {
                console.error(reason)
                console.error(reason.response.data.toString())
                this.setState({
                    resultState: reason.toString(),
                    resultStateResponse: reason.response.data.toString()
                })
            }
        )
        event.preventDefault();
    }


    render() {
        return (

            <div>
                <form onSubmit={this.handleSubmit}>
                    <Grid>
                        <Row>
                            <Col xs={16} md={8}>
                                <Panel id="crpanel" defaultExpanded>
                                    <Panel.Heading>
                                        <Panel.Title toggle>Отправка формы</Panel.Title>
                                    </Panel.Heading>
                                    <Panel.Collapse>
                                        <Panel.Body>
                                            <FormGroup controlId="username" bsSize="sm">
                                                <ControlLabel>Login (*)</ControlLabel>
                                                <FormControl
                                                    type="text"
                                                    value={this.state.username}
                                                    onChange={this.handleChange}
                                                />
                                            </FormGroup>
                                            <FormGroup controlId="password" bsSize="sm">
                                                <ControlLabel>Password (*)</ControlLabel>
                                                <FormControl
                                                    value={this.state.password}
                                                    onChange={this.handleChange}
                                                    type="password"
                                                />
                                            </FormGroup>
                                            <FormGroup controlId="fullname" bsSize="sm">
                                                <ControlLabel>ФИО (*)</ControlLabel>
                                                <FormControl
                                                    autoFocus
                                                    type="text"
                                                    value={this.state.fullname}
                                                    onChange={this.handleChange}
                                                />
                                            </FormGroup>
                                            <FormGroup controlId="email" bsSize="sm">
                                                <ControlLabel>email (*)</ControlLabel>
                                                <FormControl
                                                    type="text"
                                                    value={this.state.email}
                                                    onChange={this.handleChange}
                                                     type="email"
                                                />
                                            </FormGroup>

                                            <Button block bsSize="sm" disabled={!this.validateFormSettings()} type="submit">
                                                Отправить
                                            </Button>
                                            <Label>{this.state.resultState}</Label>
                                          <br/>
                                          <Label style={{whiteSpace:'normal',wordWrap:'break-all'}}>{this.state.resultStateResponse}</Label>

                                        </Panel.Body>
                                    </Panel.Collapse>
                                </Panel>

                            </Col>

                        </Row>
                    </Grid>
                </form>
            </div>
        );
    }
}
