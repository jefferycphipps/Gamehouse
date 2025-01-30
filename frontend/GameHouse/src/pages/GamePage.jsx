import { useContext, useEffect, useRef, useState } from "react";
import { Link, useParams } from "react-router";
import { getbyID } from "../services/APIservice";
import { wishlishContext } from "../App";
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
  const [fakeReviews, setFakeReviews] = useState([
    {
      review:
        "Lorem ipsum odor amet, consectetuer adipiscing elit. Accumsan habitant bibendum suspendisse felis conubia parturient risus. Sollicitudin laoreet mus ante accumsan, bibendum nulla sagittis. Vulputate feugiat inceptos curae accumsan efficitur ultrices efficitur nunc ad. Duis mus laoreet sodales, inceptos orci iaculis justo? Ad mauris litora placerat pulvinar erat aliquam. Dolor pharetra suspendisse cursus vitae senectus donec pulvinar ipsum. Maximus donec volutpat etiam in pulvinar erat. Mi phasellus porta cras et, tristique leo scelerisque phasellus. Himenaeos in suscipit curae diam; ridiculus nec nisl. Interdum platea per sodales tristique felis ad.",
      name: "GoldenBoy69",
      profilePic:
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQHT1bbUhvtq8v-AegejdrqfFAzLNiGqiNaeQ&s",
    },
    {
      review:
        "Lorem ipsum odor amet, consectetuer adipiscing elit. Accumsan habitant bibendum suspendisse felis conubia parturient risus. Sollicitudin laoreet mus ante accumsan, bibendum nulla sagittis. Vulputate feugiat inceptos curae accumsan efficitur ultrices efficitur nunc ad. Duis mus laoreet sodales, inceptos orci iaculis justo? Ad mauris litora placerat pulvinar erat aliquam. ",
      name: "SuperFuture",
      profilePic:
        "https://i.pinimg.com/736x/30/d5/c0/30d5c013ba8971b621b782e003ef0153.jpg",
    },
    {
      review:
        "Lorem ipsum odor amet, consectetuer adipiscing elit. Accumsan habitant bibendum suspendisse felis conubia parturient risus. Sollicitudin laoreet mus ante accumsan, bibendum nulla sagittis. Vulputate feugiat inceptos curae accumsan efficitur ultrices efficitur nunc ad. Duis mus laoreet sodales, inceptos orci iaculis justo? Ad mauris litora placerat pulvinar erat aliquam. Dolor pharetra suspendisse cursus vitae senectus donec pulvinar ipsum. Maximus donec volutpat etiam in pulvinar erat.",
      name: "BigPizza",
      profilePic:
        "https://www.simplyrecipes.com/thmb/pjYMLcsKHkr8D8tYixmaFNxppPw=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/__opt__aboutcom__coeus__resources__content_migration__simply_recipes__uploads__2019__09__easy-pepperoni-pizza-lead-3-8f256746d649404baa36a44d271329bc.jpg",
    },
  ]);

  const gameAgain = useRef();

  useEffect(() => {
    const getGameRequest = async (gameID) => {
      setIsPending(true);
      try {
        const response = await getbyID(gameID);

        const responseJSON = await response.data;
        console.log(responseJSON);
        setGame(responseJSON);
        gameAgain.current = responseJSON.gameCategories;
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

  function handleSubmit(e) {
    e.preventDefault();

    const fullReview = { review, name, profilePic };
    console.log(fullReview);
    setFakeReviews([...fakeReviews, fullReview]);
    console.log(fakeReviews);
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
              <div className="basis-1/2 flex flex-col justify-center gap-17">
                <h1 className="mb-5 text-5xl font-bold basis-3/4 text-slate-200 text-center">
                  {game.name || "Unfortunately Game Info could not be found :("}
                </h1>
                <p className="mb-5 text-slate-200 h-1/2 basis-1/4">
                  {game.gameDescription}
                </p>
                <div>
                  <div className="my-5 text-slate-200">Genres: </div>
                  <div className="text-slate-200">Platforms:</div>
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
          {fakeReviews.map((r, i) => (
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
                      <img alt="Profile Picture" src={r.profilePic} />
                    </div>
                  </Link>
                  <div className="text-sm">{r.name}</div>
                </div>
                <div className="flex flex-col basis-3/4 justify-between">
                  <p key={i} className=" mb-10">
                    {r.review}
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
