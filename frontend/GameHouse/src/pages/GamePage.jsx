import { useContext, useEffect, useState } from "react";
import { useParams } from "react-router";
import { getbyID } from "../services/APIservice";
import { wishlishContext } from "../App";

function GamePage() {
  const router = useParams();
  const { gameID } = router;
  const [game, setGame] = useState([]);

  useEffect(() => {
    const getGameRequest = async (gameID) => {
      const response = await getbyID(gameID);

      const responseJSON = await response.data;
      console.log(responseJSON);
      setGame(responseJSON);
      console.log(game);
    };
    getGameRequest(gameID).catch(console.error);
  }, [gameID]);

  const [wishlist, setWishlish] = useState(useContext(wishlishContext));
  console.log(wishlist);
  const [saved, setSaved] = useState(useContext(wishlishContext));
  console.log(saved);
  return (
    <>
      <div
        className="hero bg-base-200 min-h-[80vh]"
        style={{
          backgroundImage: `url(${game.boxArtURL})`,
        }}
      >
        <div className="hero-overlay bg-opacity-90"></div>
        <div className="w-4/5 top-0 ">
          <div className="w-full h-3/5 flex gap-10 ">
            <div className="basis-1/3 flex flex-col items-center justify-center gap-5">
              <img src={game.boxArtURL} className="size-auto" />
              <button
                onClick={(e) => {
                  e.preventDefault();
                  e.stopPropagation();
                  console.log(game?.igdbcode);
                  setWishlish([...wishlist, game?.igdbcode]);
                  console.log(wishlist);
                }}
                className="btn btn-primary rounded-3xl w-1/2 flex items-center justify-start pl-20"
              >
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  className="h-6 w-6"
                  fill="none"
                  viewBox="0 0 24 24"
                  stroke="currentColor"
                >
                  <path
                    strokeLinecap="round"
                    strokeLinejoin="round"
                    strokeWidth="2"
                    d="M4.318 6.318a4.5 4.5 0 000 6.364L12 20.364l7.682-7.682a4.5 4.5 0 00-6.364-6.364L12 7.636l-1.318-1.318a4.5 4.5 0 00-6.364 0z"
                  />
                </svg>
                Wishlist
              </button>
              <button
                onClick={(e) => {
                  e.preventDefault();
                  e.stopPropagation();
                  console.log(game?.igdbcode);
                  setSaved([...saved, game?.igdbcode]);
                  console.log(saved);
                }}
                className="btn btn-accent rounded-3xl w-1/2 flex items-center justify-start pl-20"
              >
                <svg
                  className="h-6 w-6"
                  viewBox="0 0 16 16"
                  xmlns="http://www.w3.org/2000/svg"
                  version="1.1"
                  fill="none"
                  stroke="#000000"
                >
                  <path
                    d="m12.75 7.75h-10m5-5v10"
                    strokeLinecap="round"
                    strokeLinejoin="round"
                    strokeWidth="1.5"
                  />
                </svg>
                Save
              </button>
            </div>
            <div className="basis-1/2 flex flex-col justify-center gap-20">
              <h1 className="mb-5 text-5xl font-bold basis-2/3 text-slate-200 text-center">
                {game.name}
              </h1>
              <p className="mb-5 text-slate-200 h-1/2">
                {game.gameDescription}
              </p>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}

export default GamePage;
