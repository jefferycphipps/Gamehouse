/* eslint react/prop-types: 0 */
import { useContext, useEffect, useState } from "react";
import { Link } from "react-router";
import { wishlishContext } from "../App";
import { addOwnedGame, addWishlistGame } from "../services/APIservice";

function Card(props) {
  const [wishlist, setWishlish] = useState(useContext(wishlishContext));
  //console.log(wishlist);

  const [saved, setSaved] = useState(useContext(wishlishContext));
  //console.log(saved);

  const username = localStorage.getItem("username");

  const handleWishlist = async (username, igdbcode) => {
    if (username === null) {
      alert("Must Login to add game!");
      console.log("Username is undefined. Must Login to add game!");
    } else {
      try {
        const formData = new FormData();

        formData.append("username", username);
        formData.append("igdbCode", igdbcode);
        //console.log(formData);

        alert("Game added to Wishlist!");

        const response = await addWishlistGame(formData);
      } catch (error) {
       // console.log("formData: " + formData);
       alert("Game already on Wishlist!");
        console.error("Error adding Wishlist game:", error);
      }
    }
  };

  const handleSaved = async (username, igdbcode) => {
    if (username === null) {
      alert("Must Login to add game!");
      console.log("Username is undefined. Must Login to add game!");
    } else {
      try {
        const formData = new FormData();
        formData.append("username", username);
        formData.append("igdbCode", igdbcode);
        //console.log(formData);

        alert("Game added to Saved List!");

        const response = await addOwnedGame(formData);
      } catch (error) {
        //console.log("formData: " + formData);
        alert("Game already on Saved List!");
        console.error("Error adding Saved game:", error);
      }
    }
  };

  return (
    <>
      {props.games.map((game) => (
        <>
          <Link
            to={`/games/${game?.igdbcode}`}
            className="card rounded-md bg-base-100 relative hover:bg-base-300 hover:cursor-pointer hover:scale-110 hover:z-40 w-52 h-80 mx-auto shadow-xl "
            key={game.igdbcode}
            onClick={() => window.scrollTo(0, 0)}
          >
            <figure className="w-full h-4/5">
              <img src={game.boxArtUrl} alt="Shoes" className="size-auto" />
            </figure>
            <div className="card-body p-5 flex-col justify-center items-center mx-auto">
              <h2 className="card-title text-xs text-center">{game.name}</h2>
              <div className="card-actions flex items-end ">
                <div
                  className="tooltip tooltip-left tooltip-accent"
                  data-tip="Save Game"
                >
                  <button
                    onClick={(e) => {
                      e.preventDefault();
                      e.stopPropagation();
                      console.log(props.game?.igdbcode);
                      setSaved([...saved, game?.igdbcode]);
                      console.log(saved);
                      handleSaved(username, game?.igdbcode);
                    }}
                    className="btn btn-accent btn-sm text-xs "
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
                  </button>
                </div>
                <div
                  className="tooltip tooltip-right tooltip-primary"
                  data-tip="Wishlist Game"
                >
                  <button
                    onClick={(e) => {
                      e.preventDefault();
                      e.stopPropagation();
                      console.log(game?.igdbcode);
                      setWishlish([...wishlist, game?.igdbcode]);
                      console.log(wishlist);
                      handleWishlist(username, game?.igdbcode);
                    }}
                    className="btn btn-primary btn-sm text-xs "
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
                  </button>
                </div>
              </div>
            </div>
          </Link>
        </>
      ))}
    </>
  );
}

export default Card;
