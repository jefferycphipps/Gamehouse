import { Link, useParams } from "react-router-dom";
import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { getPhoto, userPage } from "../services/APIservice";
import "../App.css";
function ProfilePage() {
  const defaultPic =
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT0IGztaTnh0lfC-HfbBGq_62Q47LFbLePQjMk1jgEZgBcgwVgkE9CzPQAb-NXECLkWrHQ&usqp=CAU";
  const [user, setUser] = useState(null);
  const navigate = useNavigate();
  const [errorMessage, setErrorMessage] = useState("");
  const username = localStorage.getItem("username");

  useEffect(() => {
    if (!username) {
      alert("You need to be logged in first.");
      navigate("/welcome");
      console.log("you got me");
      return;
    }

    const fetchUserData = async () => {
      try {
        const response = await userPage(username);
        setUser(response.data);

        setErrorMessage("");
      } catch (error) {
        setErrorMessage(`An error occurred: ${error.message}`);
        navigate("/welcome");
        console.log("you got me too");
      }
    };
    fetchUserData();
  }, [username, navigate]);

  return (
    <div className="flex flex-col mx-auto w-1/2 h-full bg-primary bg-opacity-5 mt-auto mb-20 gap-7 pb-20 rounded-md">
      {errorMessage ? (
        <h1 className="text-red-500">{errorMessage}</h1>
      ) : (
        <>
          {user ? (
            <>
              <div className="flex gap-20 my-14">
                <div className=" ml-14 w-40 h-40 rounded-full overflow-hidden">
                  <img
                    alt="Profile Picture"
                    src={
                      user.profileImage
                        ? "http://localhost:8080/image/" + username
                        : defaultPic
                    }
                    className="w-full h-full object-cover"
                  />
                </div>
                <div className="flex flex-col gap-2">
                  <p className="text-lg">Welcome, {user.username}</p>
                  <p className="text-lg">Email: {user.email}</p>
                  <Link to={"/editaccount"}>
                    <button className="btn btn-primary">
                      Edit Profile{" "}
                      <svg
                        className="h-5 w-5 text-base-100"
                        viewBox="0 0 24 24"
                        fill="none"
                        xmlns="http://www.w3.org/2000/svg"
                      >
                        <g id="SVGRepo_bgCarrier" strokeWidth="0"></g>
                        <g
                          id="SVGRepo_tracerCarrier"
                          strokeLinecap="round"
                          strokeLinejoin="round"
                        ></g>
                        <g id="SVGRepo_iconCarrier">
                          {" "}
                          <path
                            d="M21.2799 6.40005L11.7399 15.94C10.7899 16.89 7.96987 17.33 7.33987 16.7C6.70987 16.07 7.13987 13.25 8.08987 12.3L17.6399 2.75002C17.8754 2.49308 18.1605 2.28654 18.4781 2.14284C18.7956 1.99914 19.139 1.92124 19.4875 1.9139C19.8359 1.90657 20.1823 1.96991 20.5056 2.10012C20.8289 2.23033 21.1225 2.42473 21.3686 2.67153C21.6147 2.91833 21.8083 3.21243 21.9376 3.53609C22.0669 3.85976 22.1294 4.20626 22.1211 4.55471C22.1128 4.90316 22.0339 5.24635 21.8894 5.5635C21.7448 5.88065 21.5375 6.16524 21.2799 6.40005V6.40005Z"
                            stroke="currentColor"
                            strokeWidth="1.5"
                            strokeLinecap="round"
                            strokeLinejoin="round"
                          ></path>{" "}
                          <path
                            d="M11 4H6C4.93913 4 3.92178 4.42142 3.17163 5.17157C2.42149 5.92172 2 6.93913 2 8V18C2 19.0609 2.42149 20.0783 3.17163 20.8284C3.92178 21.5786 4.93913 22 6 22H17C19.21 22 20 20.2 20 18V13"
                            stroke="currentColor"
                            strokeWidth="1.5"
                            strokeLinecap="round"
                            strokeLinejoin="round"
                          ></path>{" "}
                        </g>
                      </svg>
                    </button>
                  </Link>
                </div>
              </div>

              <div className="mx-auto my-auto w-11/12 bg-base-300 p-5 rounded-md h-full">
                <p className="text-xl ">Saved Games</p>
                <div className="divider mt-2 mb-4"></div>
                <div className="flex gap-5 overflow-x-scroll w-full">
                  <Link
                    className="flex-grow-0 flex-shrink-0 mb-5 hover:scale-105 hover:opacity-80"
                    to={"/games/"}
                  >
                    <img
                      className="h-52 w-40 block rounded-sm"
                      src="https://www.cnet.com/a/img/resize/d7c9ed8cf09ba8f3c806ebd6f88e1347472b1f5b/hub/2016/06/10/de63df86-8173-40a4-bc8b-86e40df7121a/fallout.jpg?auto=webp&width=1200"
                    />
                  </Link>
                  <Link
                    className="flex-grow-0 flex-shrink-0 mb-5 hover:scale-105 hover:opacity-80"
                    to={"/games/"}
                  >
                    <img
                      className="h-52 w-40 block rounded-sm"
                      src="https://www.cnet.com/a/img/resize/d7c9ed8cf09ba8f3c806ebd6f88e1347472b1f5b/hub/2016/06/10/de63df86-8173-40a4-bc8b-86e40df7121a/fallout.jpg?auto=webp&width=1200"
                    />
                  </Link>
                  <Link
                    className="flex-grow-0 flex-shrink-0 mb-5 hover:scale-105 hover:opacity-80"
                    to={"/games/"}
                  >
                    <img
                      className="h-52 w-40 block rounded-sm"
                      src="https://www.cnet.com/a/img/resize/d7c9ed8cf09ba8f3c806ebd6f88e1347472b1f5b/hub/2016/06/10/de63df86-8173-40a4-bc8b-86e40df7121a/fallout.jpg?auto=webp&width=1200"
                    />
                  </Link>
                  <Link
                    className="flex-grow-0 flex-shrink-0 mb-5 hover:scale-105 hover:opacity-80"
                    to={"/games/"}
                  >
                    <img
                      className="h-52 w-40 block rounded-sm"
                      src="https://www.cnet.com/a/img/resize/d7c9ed8cf09ba8f3c806ebd6f88e1347472b1f5b/hub/2016/06/10/de63df86-8173-40a4-bc8b-86e40df7121a/fallout.jpg?auto=webp&width=1200"
                    />
                  </Link>
                  <Link
                    className="flex-grow-0 flex-shrink-0 mb-5 hover:scale-105 hover:opacity-80"
                    to={"/games/"}
                  >
                    <img
                      className="h-52 w-40 block rounded-sm"
                      src="https://www.cnet.com/a/img/resize/d7c9ed8cf09ba8f3c806ebd6f88e1347472b1f5b/hub/2016/06/10/de63df86-8173-40a4-bc8b-86e40df7121a/fallout.jpg?auto=webp&width=1200"
                    />
                  </Link>
                  <Link
                    className="flex-grow-0 flex-shrink-0 mb-5 hover:scale-105 hover:opacity-80"
                    to={"/games/"}
                  >
                    <img
                      className="h-52 w-40 block rounded-sm"
                      src="https://www.cnet.com/a/img/resize/d7c9ed8cf09ba8f3c806ebd6f88e1347472b1f5b/hub/2016/06/10/de63df86-8173-40a4-bc8b-86e40df7121a/fallout.jpg?auto=webp&width=1200"
                    />
                  </Link>
                </div>
              </div>
              <div className="mx-auto my-10 w-11/12 bg-base-300 p-5 rounded-md h-full">
                <p className="text-xl">Wishlisted Games </p>

                <div className="divider mt-2 mb-4"></div>
                <div className="flex gap-5 overflow-x-scroll w-full ">
                  <Link
                    className="flex-grow-0 flex-shrink-0 mb-5 hover:scale-105 hover:opacity-80"
                    to={"/games/"}
                  >
                    <img
                      className="h-52 w-40 block rounded-sm"
                      src="https://www.cnet.com/a/img/resize/d7c9ed8cf09ba8f3c806ebd6f88e1347472b1f5b/hub/2016/06/10/de63df86-8173-40a4-bc8b-86e40df7121a/fallout.jpg?auto=webp&width=1200"
                    />
                  </Link>
                  <Link
                    className="flex-grow-0 flex-shrink-0 mb-5 hover:scale-105 hover:opacity-80"
                    to={"/games/"}
                  >
                    <img
                      className="h-52 w-40 block rounded-sm"
                      src="https://www.cnet.com/a/img/resize/d7c9ed8cf09ba8f3c806ebd6f88e1347472b1f5b/hub/2016/06/10/de63df86-8173-40a4-bc8b-86e40df7121a/fallout.jpg?auto=webp&width=1200"
                    />
                  </Link>
                  <Link
                    className="flex-grow-0 flex-shrink-0 mb-5 hover:scale-105 hover:opacity-80"
                    to={"/games/"}
                  >
                    <img
                      className="h-52 w-40 block rounded-sm"
                      src="https://www.cnet.com/a/img/resize/d7c9ed8cf09ba8f3c806ebd6f88e1347472b1f5b/hub/2016/06/10/de63df86-8173-40a4-bc8b-86e40df7121a/fallout.jpg?auto=webp&width=1200"
                    />
                  </Link>
                  <Link
                    className="flex-grow-0 flex-shrink-0 mb-5 hover:scale-105 hover:opacity-80"
                    to={"/games/"}
                  >
                    <img
                      className="h-52 w-40 block rounded-sm"
                      src="https://www.cnet.com/a/img/resize/d7c9ed8cf09ba8f3c806ebd6f88e1347472b1f5b/hub/2016/06/10/de63df86-8173-40a4-bc8b-86e40df7121a/fallout.jpg?auto=webp&width=1200"
                    />
                  </Link>
                  <Link
                    className="flex-grow-0 flex-shrink-0 mb-5 hover:scale-105 hover:opacity-80"
                    to={"/games/"}
                  >
                    <img
                      className="h-52 w-40 block rounded-sm"
                      src="https://www.cnet.com/a/img/resize/d7c9ed8cf09ba8f3c806ebd6f88e1347472b1f5b/hub/2016/06/10/de63df86-8173-40a4-bc8b-86e40df7121a/fallout.jpg?auto=webp&width=1200"
                    />
                  </Link>
                  <Link
                    className="flex-grow-0 flex-shrink-0 mb-5 hover:scale-105 hover:opacity-80"
                    to={"/games/"}
                  >
                    <img
                      className="h-52 w-40 block rounded-sm"
                      src="https://www.cnet.com/a/img/resize/d7c9ed8cf09ba8f3c806ebd6f88e1347472b1f5b/hub/2016/06/10/de63df86-8173-40a4-bc8b-86e40df7121a/fallout.jpg?auto=webp&width=1200"
                    />
                  </Link>
                </div>
              </div>
            </>
          ) : (
            <h1>Loading...</h1>
          )}
        </>
      )}
    </div>
  );
}

export default ProfilePage;
