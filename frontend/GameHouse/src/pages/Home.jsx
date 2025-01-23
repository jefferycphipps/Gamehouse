/* eslint react/prop-types: 0 */

// import { useState } from "react";
// import { useEffect } from "react";
import { useOutletContext } from "react-router";
import Card from "../components/Card";

import Carousel2 from "../components/Carousel";

function Home() {
  const context = useOutletContext();
  const slides = [
    {
      id: "152063",
      img: "https://cdn1.epicgames.com/offer/0a9e3c5ab6684506bd624a849ca0cf39/EGS_DeathStrandingDirectorsCut_KOJIMAPRODUCTIONS_S3_2560x1440-fe4e51f1801fba36e452aa3466625789",
      name: "Death Stranding: Director's Cut",
    },
    {
      id: "109462",
      img: "https://images.alphacoders.com/136/thumb-1920-1366994.jpeg",
      name: "Animal Crossing: New Horizons",
    },
    {
      id: "132516",
      img: "https://www.uploadvr.com/content/images/size/w1024/format/webp/2024/10/PhasmophobiaKeyArt16x9-1.png",
      name: "Phasmophobia",
    },
    {
      id: "217590",
      img: "https://images6.alphacoders.com/135/1359106.png",
      name: "Tekken 8",
    },
  ];

  return (
    <>
      <div>
        {context.searchValue ? (
          <h1 className="mx-auto w-3/5 text-xl mt-20 mb-5">
            Results for: {context.searchValue}
          </h1>
        ) : (
          <></>
        )}
      </div>
      <>
        {context.searchValue ? (
          <div className="m-auto ">
            <div className="grid grid-cols-3 w-3/5 mx-auto gap-4">
              <Card games={context.games} />
            </div>
          </div>
        ) : (
          <div className="mx-auto w-3/5">
            <h1 className="text-6xl font-bold mt-10 mb-5 tracking-wide">
              Developer Picks
            </h1>
            <h2 className="text-md font-bold my-5 ">
              Check out the games hand-picked by our dev team
            </h2>
            <div className="w-full aspect-[6/3] my-0 mx-auto">
              <Carousel2 images={slides} />
            </div>
            <div className="h-[1000px]"></div>
          </div>
        )}
      </>
      {context.searchValue ? <div className="flex my-10"></div> : <></>}
    </>
  );
}

export default Home;
