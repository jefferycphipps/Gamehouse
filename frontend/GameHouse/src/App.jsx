import { createBrowserRouter, RouterProvider } from "react-router";

import Home from "./pages/Home";
import AppLayout from "./components/AppLayout";
import GamePage from "./pages/GamePage";
import ProfilePage from "./pages/ProfilePage";

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
    ],
  },
]);

function App() {
  return <RouterProvider router={router} />;
}

export default App;
