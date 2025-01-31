import { createBrowserRouter, RouterProvider } from "react-router";
import { createContext, useState } from "react";

import Home from "./pages/Home";
import AppLayout from "./components/AppLayout";
import GamePage from "./pages/GamePage";
import ProfilePage from "./pages/ProfilePage";
import SignInPage from "./pages/SignInPage";
import { startup } from "./services/APIservice";
import RegisterPage from "./pages/RegisterPage";
import EditProfilePic from "./pages/EditProfilePic";
import DeleteUser from "./pages/DeleteUser";

const router = createBrowserRouter([
  {
    element: <AppLayout />,
    children: [
      {
        path: "/",
        element: <Home />,
      },
      {
        path: "/games/:gameID",
        element: <GamePage />,
      },
      {
        path: "/profile/:username",
        element: <ProfilePage />,
      },
      {
        path: "/welcome",
        element: <SignInPage />,
      },
      {
        path: "/register",
        element: <RegisterPage />,
      },
      {
        path: "/editaccount",
        element: <EditProfilePic />,
      },
      {
        path: "/deleteaccount",
        element: <DeleteUser />,
      },
    ],
  },
]);

export const response = () => {
  const start = "go";
  const repsonser = startup(start);
  console.log(repsonser);
};

export const wishlishContext = createContext();

function App() {
  const [wishlist, setWishlish] = useState([]);
  const [saved, setSaved] = useState([]);
  return (
    <>
      <wishlishContext.Provider value={(wishlist, saved)}>
        {response()}
        <RouterProvider router={router} />
      </wishlishContext.Provider>
    </>
  );
}

export default App;
//

//<><Test /></>
