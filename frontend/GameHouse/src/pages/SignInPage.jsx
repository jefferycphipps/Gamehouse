import {Form, Field, ErrorMessage, FormikProvider, Formik} from "formik";
import axios from "axios";
import {useState, useEffect} from "react";
import * as Yup from "yup";
import {useNavigate} from "react-router-dom";
function SignInPage() {

const navigate = useNavigate();
    const handleSubmit = async (values, {setSubmitting}) => {

        try{
            const response = await axios.post("http://localhost:8080/user/login",{
                username: values.username,
                password: values.password
                });

            localStorage.setItem("username", values.username)

            alert("login successful");
            navigate(`/profile/${values.username}`);

            } catch (error) {
                console.error("login Failed", error)
            alert("Login failed, wrong Username or Password.")

            }finally {
                setSubmitting(false);
            }
        }

    const validation = Yup.object({
        username: Yup.string()
        .required("Username is required"),
        password: Yup.string()
        .required("Password is required")
        });



  return (
    <div className="mt-20 text-2xl mx-auto w-4/5 text-center">
      <h1>Sign In</h1>
      <Formik initialValues={{username:"", password:""}}
      validationSchema={validation}
      onSubmit={handleSubmit}>
          {({ isSubmitting }) => (
              <Form>
                 <div className="mb-5">
                     <label htmlFor="username">Username: </label>
                     <Field type="text"
                            name="username"
                            id="username"/>
                            <ErrorMessage name="username" component="div" className="text-red-500" />
                 </div>
                 <div className="mb-5">
                     <label htmlFor="password">Password: </label>
                     <Field type="password"
                            name="password"
                            id="password"/>
                            <ErrorMessage name="password" component="div" className="text-red-500" />
                 </div>

                     <button type="submit"  disabled={isSubmitting} className="btn btn-primary">
                        {isSubmitting ? "Logging In" : "Sign In"}
                     </button>
              </Form>
              )}
      </Formik>
    </div>
  );
}

export default SignInPage;
