import {useParams} from "react-router-dom";


function ProfilePage() {
const {username} = useParams();



const storedUsername = localStorage.getItem("username");


  return (
    <div className="mt-20 text-2xl mx-auto w-4/5 text-center">
      <h1>welcome {storedUsername}</h1>

    </div>
  );
}

export default ProfilePage;
