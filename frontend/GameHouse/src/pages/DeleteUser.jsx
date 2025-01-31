import {useParams} from "react-router-dom";
import React, { useEffect, useState } from "react";
import {useNavigate} from "react-router-dom";
import { deleteAccount } from "../services/APIservice";
import "../App.css";

function DeleteUser(){
const [errorMessage, setErrorMessage] = useState("");
const username = localStorage.getItem("username");
const navigate = useNavigate();

const deleteAcc = async() =>{
  

  try{
    const formData = new FormData();
    formData.append('username', username);
    console.log(username);
    console.log(formData);
    
    const response = await deleteAccount(formData);
    console.log(response.data);
    navigate("/welcome");
  }catch(error){
    setErrorMessage(`An error occurred: ${error.message}`);
  }

}


return (

    <>
    <Div>Click here to delete your account!</Div>
        <button className="btn btn-primary" onClick={deleteAcc}>Delete Account</button>
    </>
);

}

export default DeleteUser;