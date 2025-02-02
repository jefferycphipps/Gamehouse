import { useContext, useEffect, useRef, useState } from "react";
import { Link, useParams } from "react-router";
import { addOwnedGame, addWishlistGame, getbyID, getReviewsByIgdb, saveReview } from "../services/APIservice";
import { response, wishlishContext } from "../App";
import M from "../assets/M.png";
import T from "../assets/T.png";
import E from "../assets/E.png";
import RP from "../assets/RP.png";
import E10 from "../assets/E10+.png";
import EC from "../assets/EC.png";
import A from "../assets/A.png";

function GamePage() {
  const router = useParams();
  const { gameID } = router;
  const [game, setGame] = useState([]);
  const [review, setReview] = useState("");
  const [name, setName] = useState("OIIA");
  const [profilePic, setProfilePic] = useState(
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT0IGztaTnh0lfC-HfbBGq_62Q47LFbLePQjMk1jgEZgBcgwVgkE9CzPQAb-NXECLkWrHQ&usqp=CAU"
  );
  const [isPending, setIsPending] = useState(false);
  

  useEffect(() => {
    const getGameRequest = async (gameID) => {
      setIsPending(true);
      try {
        const response = await getbyID(gameID);

        const responseJSON = await response.data;
        console.log(responseJSON);
        setGame(responseJSON);

        console.log(game);
      } catch (error) {
        console.log(error);
      } finally {
        setIsPending(false);
      }
    };
    getGameRequest(gameID).catch(console.error);
  }, [gameID]);


  const [wishlist, setWishlish] = useState(useContext(wishlishContext));
  console.log(wishlist);
  const [saved, setSaved] = useState(useContext(wishlishContext));
  console.log(saved);

  const username = localStorage.getItem("username");

  const handleAddWishlist = async (username, igdbcode) => {

    if (username === null) {
      alert("Must Login to add game!");
      console.log("Username is undefined. Must Login to add game!");

    } else {

      try {
        const formData = new FormData();
        formData.append('username', username);
        formData.append('igdbCode', igdbcode);
        console.log(formData);
        alert("Game added to Wishlist!");
        
        const response = await addWishlistGame(formData);
  
      } catch (error) {
        console.log("formData: " + formData);
        console.error('Error adding Wishlist game:', error);
      }
    }
  };

  const handleSaveReview = async (igdbCode, username, gameReview) => {

    if (username === null) {

      alert("Must Login to add game!");
      console.log("Username is undefined. Must Login to add Game Review!");

    } else {

      try {
        const formData = new FormData();
        formData.append('igdbCode', igdbCode);
        formData.append('username', username);
        formData.append('gameReview', gameReview);
        console.log(formData);

        alert("Game Review saved!");

        const responseSaveReview = await saveReview(formData);

        setGameReviewSaved(responseSaveReview.data);
        console.log(typeof responseSaveReview.data);
        console.log(responseSaveReview.data);
        console.log(gameReviewSaved);

        const response = await getReviewsByIgdb(igdbCode);
        console.log(response.data);

        setGameReviews(response.data);
        console.log(gameReviews);


      } catch (error) {
        // console.log("formData: " + formData);
        console.error('Error adding Game Review:', error);
      }
    }
  };


  useEffect(() => {
    const fetchReviews = async (gameID) => {
      try {
        const response = await getReviewsByIgdb(gameID);
        console.log(response.data);

        setGameReviews(response.data);
        console.log(gameReviews);

      } catch (error) {
        // console.log(response.data);
        console.log(error);
      }
    }
    fetchReviews(gameID).catch(console.error);
  }, [gameReviewSaved]);



  useEffect(() => {
    console.log("Updated Reviews:", gameReviews);
  }, [gameReviews]);


  const handleAddSaved = async (username, igdbcode) => {

    if (username === null) {
      alert("Must Login to add game!");
      console.log("Username is undefined. Must Login to add game!");

    } else {

      try {
        const formData = new FormData();
        formData.append('username', username);
        formData.append('igdbCode', igdbcode);
        console.log(formData);
        alert("Game added to Saved List!");

        const response = await addOwnedGame(formData);
  
      } catch (error) {
        console.log("formData: " + formData);
        console.error('Error adding Saved game:', error);
      }
    }
  };


  function handleSubmit(e) {
    e.preventDefault();

      const fullReview = { review, name, profilePic };
      console.log(fullReview);

      handleSaveReview (game.igdbCode, username, fullReview.review);

      setReview("");
  }




  function ratingImg() {
    if (game.gameRating == "M") {
      return M;
    } else if (game.gameRating == "T") {
      return T;
    } else if (game.gameRating == "E") {
      return E;
    } else if (game.gameRating == "AO") {
      return A;
    } else if (game.gameRating == "dummy") {
      return RP;
    } else if (game.gameRating == "E10") {
      return E10;
    } else if (game.gameRating == "EC") {
      return EC;
    } else {
      return "";
    }
  }

  return (
    <>
      <div
        className="hero bg-base-200 min-h-[80vh]"
        style={{
          backgroundImage: `url(${game.boxArtURL})`,
        }}
      >
        <div className="hero-overlay bg-opacity-90"></div>
        {isPending ? (
          <span className="loading loading-spinner loading-lg mx-auto "></span>
        ) : (
          <div className="w-4/5 top-0 ">
            <div className="w-full h-3/5 flex gap-10 ">
              <div className="basis-1/3 flex flex-col items-center justify-center gap-5 ">
                <div className="relative">
                  <img src={game.boxArtURL || Error} className="size-auto" />
                  <img
                    src={ratingImg()}
                    className="absolute h-15 w-10 bottom-1 left-1"
                  />
                </div>
                <button
                  onClick={(e) => {
                    e.preventDefault();
                    e.stopPropagation();
                    console.log(game?.igdbcode);
                    setWishlish([...wishlist, game?.igdbcode]);
                    console.log(wishlist);
                    handleAddWishlist(username, game.igdbCode);
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
                    handleAddSaved(username, game.igdbCode);
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
              <div className="basis-1/2 flex flex-col justify-center gap-17">
                <h1 className="mb-5 text-5xl font-bold basis-3/5 text-slate-200 text-center">
                  {game.name || "Unfortunately Game Info could not be found :("}
                </h1>
                <p className="mb-5 text-slate-200 h-1/2 basis-1/3">
                  {game.gameDescription}
                </p>
                <div className="flex flex-col gap-2 mt-10 mb-5">
                  <div className="text-slate-200">
                    Genres: &nbsp;
                    {game.gameCategories?.map((genre) => `${genre.name}, `)}
                  </div>
                  <div className="text-slate-200">
                    Platforms: &nbsp;
                    {game.gamePlatforms?.map((plat) => `${plat.name}, `)}
                  </div>
                </div>
              </div>
            </div>
          </div>
        )}
      </div>
      <div className="mx-auto w-3/5 flex flex-col">
        <div className="text-6xl font-bold mt-10 mb-10 tracking-wide">
          Reviews
        </div>

        {/* <input
          type="text"
          placeholder="Write a Review"
          className="input input-bordered input-primary h-48 w-full my-10 max-w-xl mx-auto justify-center"
          /> */}
        <form
          onSubmit={handleSubmit}
          className="ml-auto justify-center w-full max-w-xl flex flex-col"
        >
          <textarea
            value={review}
            className="textarea textarea-primary w-full max-w-xl my-10 h-48"
            placeholder="Write a Review"
            onChange={(e) => setReview(e.target.value)}
          ></textarea>
          <button type="submit" className="btn btn-primary ml-auto w-1/4 ">
            Submit
          </button>
        </form>
        <div className="flex flex-col gap-5 my-10">
          {gameReviews.map((r, i) => (
            <>
              <div className="flex p-5 justify-between ">
                <div className="flex flex-col justify-start items-start gap-3">
                  <Link
                    to={"/profile/:username"}
                    tabIndex={0}
                    role="button"
                    className="btn btn-ghost btn-circle avatar"
                  >
                    <div className="w-10 rounded-full">
                      <img alt="Profile Picture" src={("http://localhost:8080/image/"+r.username) ? ("http://localhost:8080/image/"+r.username):(profilePic)} />
                    </div>
                  </Link>
                  <div className="text-sm">{r.username}</div>
                </div>
                <div className="flex flex-col basis-3/4 justify-between">
                  <p key={i} className=" mb-10">
                    {r.gameReview}
                  </p>
                  <div className="divider"></div>
                </div>
              </div>
            </>
          ))}
        </div>
      </div>
    </>
  );
}

export default GamePage;
