import { useParams } from "react-router-dom";
import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { photo, userPage } from "../services/APIservice";
import "../App.css";

function EditProfilePic() {
  const [user, setUser] = useState(null);
  const [file, setFile] = useState("");
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

  const setPhoto = async (e) => {
    console.log(e.target.files);
    setFile(e.target.files[0]);
  };
  const savePhoto = async () => {
    try {
      const formData = new FormData();
      formData.append("user", username);
      formData.append("image", file);
      const response = await photo(formData);
      navigate(`/profile/${username}`);
      window.location.reload();
    } catch (error) {
      setErrorMessage(`An error occurred: ${error.message}`);
    }
  };
  return (
    <div className="flex flex-col mx-auto gap-5">
      <div className="w-40 h-40 rounded-full overflow-hidden mx-auto">
        <img
          alt="Profile Picture"
          src={"http://localhost:8080/image/" + username}
          className="w-full h-full object-cover shadow-md shadow-indigo-500/50"
        />
      </div>
      <label className="form-control w-full max-w-xs">
        <div className="label">
          <span className="label-text">Choose Profile Picture</span>
        </div>
        <input
          type="file"
          accept="image/*"
          name="file"
          className="file-input file-input-bordered w-full max-w-xs"
          onChange={setPhoto}
        />
        <button type="submit" className="btn btn-primary" onClick={savePhoto}>
          Submit!
        </button>
        <div className="label"></div>
      </label>
    </div>
  );
}

export default EditProfilePic;
