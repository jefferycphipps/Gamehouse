import {useParams} from "react-router-dom";


function ProfilePage() {
const {username} = useParams();
console.log(username);


const storedUsername = localStorage.getItem("username");
console.log(storedUsername);

  return (
    <div className="mt-20 text-2xl mx-auto w-4/5 text-center">
      <h1>welcome {storedUsername}</h1>

    </div>
  );
}

export default ProfilePage;
