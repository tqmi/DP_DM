import React, { useEffect, useState } from "react";
import { Container,Row,Col,Form ,Button} from 'react-bootstrap';

import { auth } from '../../firebase';
import { useAuthState } from 'react-firebase-hooks/auth';

import { useOperationMethod } from 'react-openapi-client';

import sendReqWithToken from "../SendReqWithToken";

function UserPage(props) {

    const [user] = useAuthState(auth);

    const [account, setAccount] = useState(null);
    const [getAccount,{ loading, data, error }] = useOperationMethod('getUserInfo');

    const [updateAccount,{ loading2, data2, error2 }] = useOperationMethod('updateUser');

    const [deleteAccount,{ loading3, data3, error3 }] = useOperationMethod('deleteUser');

    useEffect(() => { 
        sendReqWithToken(user,getAccount,null,null,{},getAccountData);
       }, 
       []
    )

    const getAccountData = (resp) => {
        if(resp){
          setAccount(resp.data);
        }
        else
        {
          setAccount(null);
        }
      }
    
  
    const UpdateProfileHandler=(event)=>{


        let newUserInfo = {
            "name": event.target.name.value,
            "email": event.target.email.value,
            "address": event.target.address.value,
            "phone": event.target.phone.value,
            "accesslevel": "string",
            "type": "User",
            "institutionlink": event.target.institutionlink.value,
            "cnp": event.target.cnp.value
        };

        sendReqWithToken(user,updateAccount,null,newUserInfo,{},getAccountData);

        sendReqWithToken(user,getAccount,null,null,{},getAccountData);
    
    }

    function deleteAccountOnClick() {
        sendReqWithToken(user,deleteAccount,null,null,{},() => auth.signOut());
      }


    if(account){
    if(account.institutionlink == "NAF")
        return (
            <Container>
            <Row>
            <Col>
                <h1>User Profile</h1>
                <Form className="form" onSubmit = {UpdateProfileHandler}>
                    <Form.Group controlId="formCategory1">
                        <Form.Label>Username</Form.Label>
                        <Form.Control  name="name" type="text" defaultValue={account ? account.name : ''}/>
                    </Form.Group>
                    
                    <Form.Group controlId="formCategory2">
                        <Form.Label>Email</Form.Label>
                        <Form.Control  name="email" type="email" defaultValue={account ? account.email : ''} />
                    </Form.Group>

                    <Form.Group controlId="formCategory3">
                        <Form.Label>Address</Form.Label>
                        <Form.Control  name="address" type="text" defaultValue={account ? account.address : ''}/>
                    </Form.Group>

                    <Form.Group controlId="formCategory4">
                        <Form.Label>Phone</Form.Label>
                        <Form.Control  name="phone" type="text" defaultValue={account ? account.phone : ''}/>
                    </Form.Group>

                    <Form.Group controlId="formCategory5">
                        <Form.Label>CNP</Form.Label>
                        <Form.Control  name="cnp" type="text" defaultValue={account ? account.cnp : ''} disabled/>
                    </Form.Group>
                    
                    <Form.Group controlId="formCategory6">
                        <Form.Label>Institution</Form.Label>
                        <Form.Control  name="institutionlink" type="text" defaultValue={account ? account.institutionlink : ''}/>
                    </Form.Group>
                    
                    
                    
                    <Row>
                        <Button type="submit" variant="primary">Update Profile</Button>
                        
                        <Col md={{ offset: 1 }}> 
                            <Button variant="danger" onClick={deleteAccountOnClick}>Delete Profile</Button> 
                        </Col>
                    </Row>
                </Form>
            </Col>

        </Row>
        </Container>
    )
    else
            return(
                <Container>
                <Row>
                <Col>
                    <h1>User Profile</h1>
                    <Form className="form" onSubmit = {UpdateProfileHandler}>
                        <Form.Group controlId="formCategory1">
                            <Form.Label>Username</Form.Label>
                            <Form.Control   name="name" type="text" defaultValue={account ? account.name : ''} />
                        </Form.Group>
                        
                        <Form.Group controlId="formCategory2">
                            <Form.Label>Email</Form.Label>
                            <Form.Control  name="email" type="email" defaultValue={account ? account.email : ''} />
                        </Form.Group>

                        <Form.Group controlId="formCategory3">
                            <Form.Label>Address</Form.Label>
                            <Form.Control  name="address" type="text" defaultValue={account ? account.address : ''}/>
                        </Form.Group>

                        <Form.Group controlId="formCategory4">
                            <Form.Label>Phone</Form.Label>
                            <Form.Control  name="phone" type="text" defaultValue={account ? account.phone : ''}/>
                        </Form.Group>

                        <Form.Group controlId="formCategory5">
                            <Form.Label>CNP</Form.Label>
                            <Form.Control  name="cnp" type="text" defaultValue={account ? account.cnp : ''} disabled/>
                         </Form.Group>
                    
                        <Form.Group controlId="formCategory6">
                            <Form.Label>Institution</Form.Label>
                            <Form.Control  name="institutionlink" type="text" defaultValue={account ? account.institutionlink : ''}/>
                        </Form.Group>
                        
                        
                        
                        <Row>
                            <Button type="submit" variant="primary">Update Profile</Button>
                            
                            <Col md={{ offset: 1 }}> 
                                <Button variant="danger" onClick={deleteAccountOnClick}>Delete Profile</Button> 
                            </Col>
                        </Row>
                    </Form>
                </Col>

                </Row>
                </Container>
            );
    }
    else{
        return <div>Loading...</div>
    }


}

   export default UserPage;