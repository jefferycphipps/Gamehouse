import { Form, Field, ErrorMessage, FormikProvider, Formik } from "formik";
import axios from "axios";
import { useState, useEffect } from "react";
import * as Yup from "yup";
import { useNavigate } from "react-router-dom";
import { loginUser } from "../services/APIservice";
import { load } from "recaptcha-v3";

function SignInPage() {
  const navigate = useNavigate();
  const [username, setUsername] = useState(
    localStorage.getItem("username") || ""
  );
  const [recaptchaToken, setRecaptchaToken] = useState("");
  const siteKey = "6LeD3a8qAAAAAIV1IMOwHogeJq0_Vwt0c6ez9LAO";

  const handleSubmit = async (values, { setSubmitting }) => {
    const recaptchaInstance = await load(siteKey);
    const token = await recaptchaInstance.execute("login");
    setRecaptchaToken(token);
    console.log(token);
    try {
      const response = await loginUser({
        username: values.username,
        password: values.password,
        recaptcha: token,
      });

      localStorage.setItem("username", values.username);

      alert("login successful");
      navigate(`/profile/${values.username}`);
    } catch (error) {
      console.error("login Failed", error);
      alert("Login failed, wrong Username or Password.");
    } finally {
      setSubmitting(false);
    }
  };

  const validation = Yup.object({
    username: Yup.string().required("Username is required"),
    password: Yup.string().required("Password is required"),
  });

  return (
    <div className="mt-20 text-2xl mx-auto w-1/4 text-center bg-base-300 p-5 rounded-lg">
      <h1 className="my-5">Sign In</h1>
      <Formik
        initialValues={{ username: "", password: "" }}
        validationSchema={validation}
        onSubmit={handleSubmit}
      >
        {({ isSubmitting }) => (
          <Form>
            <div className="mb-5">
              <label
                htmlFor="username"
                className="input input-bordered flex items-center gap-2"
              >
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  viewBox="0 0 16 16"
                  fill="currentColor"
                  className="h-4 w-4 opacity-70"
                >
                  <path d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6ZM12.735 14c.618 0 1.093-.561.872-1.139a6.002 6.002 0 0 0-11.215 0c-.22.578.254 1.139.872 1.139h9.47Z" />
                </svg>
                Username:
                <Field
                  type="text"
                  name="username"
                  id="username"
                  className="grow"
                />
                <ErrorMessage
                  name="username"
                  component="div"
                  className="text-red-500"
                />
              </label>
            </div>
            <div className="mb-5">
              <label
                className="input input-bordered flex items-center gap-2"
                htmlFor="password"
              >
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  viewBox="0 0 16 16"
                  fill="currentColor"
                  className="h-4 w-4 opacity-70"
                >
                  <path
                    fillRule="evenodd"
                    d="M14 6a4 4 0 0 1-4.899 3.899l-1.955 1.955a.5.5 0 0 1-.353.146H5v1.5a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1-.5-.5v-2.293a.5.5 0 0 1 .146-.353l3.955-3.955A4 4 0 1 1 14 6Zm-4-2a.75.75 0 0 0 0 1.5.5.5 0 0 1 .5.5.75.75 0 0 0 1.5 0 2 2 0 0 0-2-2Z"
                    clipRule="evenodd"
                  />
                </svg>
                Password:{" "}
                <Field
                  className="grow"
                  type="password"
                  name="password"
                  id="password"
                />
                <ErrorMessage
                  name="password"
                  component="div"
                  className="text-red-500 text-center"
                />
              </label>
            </div>

            <button
              type="submit"
              disabled={isSubmitting}
              className="btn btn-primary"
            >
              {isSubmitting ? "Logging In" : "Sign In"}
            </button>
          </Form>
        )}
      </Formik>
    </div>
  );
}

export default SignInPage;
