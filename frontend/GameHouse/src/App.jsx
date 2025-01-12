import { createBrowserRouter, RouterProvider } from "react-router";

import Home from "./pages/Home";
import AppLayout from "./components/AppLayout";
import GamePage from "./pages/GamePage";
import ProfilePage from "./pages/ProfilePage";
import SignInPage from "./pages/SignInPage";
import { startup } from "./services/APIservice";
import Test from "./pages/Test";
import RegisterPage from "./pages/RegisterPage";

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
        path: "/profile/:profileId",
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
    ],
  },
]);
export const response =  () => {
  const start = "go";
  const repsonser =  startup(start);
  console.log(repsonser);
};

function App() {
  return (
    <>{response()}
    <RouterProvider router={router} />
    </>
  );

}

export default App;
//
//<><Test /></>