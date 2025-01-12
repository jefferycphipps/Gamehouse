import {Form, Field, ErrorMessage, FormikProvider, Formik} from "formik";
import axios from "axios";
import {useState, useEffect} from "react";
import * as Yup from "yup";
import {useNavigate} from "react-router-dom";


function RegisterPage(){

const navigate = useNavigate();
const  handleSubmit = async (values, {setSubmitting}) => {

    try{
         const response = await axios.post("http://localhost:8080/user/register",{
                        name: values.name,
                        username: values.username,
                        email: values.email,
                        password: values.password,
                        verifyPassword: values.verifyPassword
                        });

                    localStorage.setItem("username", values.username)

                    alert("Register successful");
                    navigate(`/profile/${values.username}`);

                    } catch (error) {
                        console.error("Register Failed", error)
                        alert("Register failed.")

                    }finally {
                        setSubmitting(false)
        }
    };
const validation = Yup.object({
    name: Yup.string()
    .required("Name is required")
    .min(3, "Name must be at least 3 characters long."),
    username: Yup.string()
    .required("Username is required")
    .min(3, "Username must be at least 3 characters"),
    email: Yup.string()
    .email("Invalid email address")
    .required("Email is required"),
    password: Yup.string()
    .required("Password is required")
    .min(3, "Password must be at least 3 characters"),
    verifyPassword: Yup.string()
    .required("Please verify your password")
    .oneOf([Yup.ref("password")], "Passwords must match")
    })


return (
    <div className="mt-20 text-2xl mx-auto w-4/5 text-center">
       <h1>Register</h1>
       <Formik initialValues={{name:"", username:"", email:"", password:"", verifyPassword:""}}
       validationSchema={validation}
       onSubmit={handleSubmit}>
           {({ isSubmitting }) =>(
        <Form>
            <div className="mb-5">
                           <label htmlFor="name">Name: </label>
                              <Field type="text"
                                name="name"
                                id="name"/>
                                <ErrorMessage name="name" component="div" className="text-red-500" />
                       </div>

           <div className="mb-5">
               <label htmlFor="username">Username: </label>
                  <Field type="text"
                    name="username"
                    id="username"/>
                    <ErrorMessage name="username" component="div" className="text-red-500" />
           </div>

           <div className="mb-5">
                          <label htmlFor="email">Email: </label>
                             <Field type="email"
                               name="email"
                               id="email"/>
                               <ErrorMessage name="email" component="div" className="text-red-500" />
                      </div>

           <div className="mb-5">
               <label htmlFor="password">Password: </label>
                  <Field type="password"
                    name="password"
                    id="password"/>
                    <ErrorMessage name="password" component="div" className="text-red-500" />
           </div>

           <div className="mb-5">
                <label htmlFor="verifyPassword">Verify Password: </label>
                  <Field type="password"
                     name="verifyPassword"
                     id="verifyPassword"/>
                     <ErrorMessage name="verifyPassword" component="div" className="text-red-500" />
            </div>

             <button type="submit"  disabled={isSubmitting} className="btn btn-primary">
                 {isSubmitting ? "Registering" : "Register"}
              </button>
        </Form>
        )}
       </Formik>
    </div>

    )
}


export default RegisterPage;