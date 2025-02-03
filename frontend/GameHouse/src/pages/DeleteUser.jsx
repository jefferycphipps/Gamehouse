import { useParams } from "react-router-dom";
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { deleteAccount, userPage } from "../services/APIservice";
import "../App.css";

function DeleteUser() {
  const [errorMessage, setErrorMessage] = useState("");
  const username = localStorage.getItem("username");
  const navigate = useNavigate();

  useEffect(() => {
    if (!username) {
      alert("You need to be logged in first.");
      navigate("/welcome");
      //console.log("you got me");
      return;
    }

    const fetchUserData = async () => {
      try {
        const response = await userPage(username);
        setErrorMessage("");
      } catch (error) {
        setErrorMessage(`An error occurred: ${error.message}`);
        navigate("/welcome");
        //console.log("you got me too");
      }
    };
    fetchUserData();
  }, [username, navigate]);

  const deleteAcc = async () => {
    try {
      const formData = new FormData();
      formData.append("username", username);

      const response = await deleteAccount(formData);
      alert("Account Successfully Deleted");
      navigate(0);
      navigate("/welcome");
      window.location.reload();
    } catch (error) {
      setErrorMessage(`An error occurred: ${error.message}`);
    }
  };

  return (
    <div className="flex flex-col mx-auto gap-2">
      <div>Click here to delete your account!</div>
      {/* Open the modal using document.getElementById('ID').showModal() method */}
      <button
        className="btn btn-error"
        onClick={() => document.getElementById("my_modal_5").showModal()}
      >
        Delete my Account
      </button>
      <dialog id="my_modal_5" className="modal modal-bottom sm:modal-middle">
        <div className="modal-box">
          <h3 className="font-bold text-lg">Are you sure?</h3>
          <p className="py-4">
            Once you press the delete button, you will no longer have access to
            your account!
          </p>
          <div className="modal-action flex justify-between">
            <button className="btn btn-error" onClick={deleteAcc}>
              Delete Account
            </button>
            <form method="dialog">
              {/* if there is a button in form, it will close the modal */}
              <button className="btn btn-primary">Cancel</button>
            </form>
          </div>
        </div>
      </dialog>
    </div>
  );
}

export default DeleteUser;
