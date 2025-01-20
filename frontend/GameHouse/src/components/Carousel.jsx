/* eslint react/prop-types: 0 */

import { useState } from "react";

function Carousel({ children: slides }) {
  const [current, setCurrent] = useState(0);

  const next = () =>
    setCurrent((curr) => (curr === slides.length - 1 ? 0 : curr + 1));
  console.log(current);

  const prev = () =>
    setCurrent((curr) => (curr === 0 ? slides.length - 1 : curr - 1));
  console.log(current);

  return (
    <div className="overflow-hidden relative rounded-3xl">
      <div
        className="  flex transition-transform ease-out duration-500"
        style={{ transform: `translateX(-${current * 100}%)` }}
      >
        {slides}
      </div>
      <div className="absolute inset-0 flex items-center justify-between p-4">
        <button onClick={prev} className="btn btn-circle p-1 z-50">
          <svg
            width="40px"
            height="40px"
            viewBox="0 0 20 20"
            xmlns="http://www.w3.org/2000/svg"
          >
            <path
              d="M12 16a.997.997 0 01-.707-.293l-5-5a.999.999 0 010-1.414l5-5a.999.999 0 111.414 1.414L8.414 10l4.293 4.293A.999.999 0 0112 16z"
              fill="#5C5F62"
            />
          </svg>
        </button>
        <button onClick={next} className="btn btn-circle z-50">
          <svg
            width="40px"
            height="40px"
            viewBox="0 0 20 20"
            xmlns="http://www.w3.org/2000/svg"
          >
            <path
              d="M8 16a.999.999 0 01-.707-1.707L11.586 10 7.293 5.707a.999.999 0 111.414-1.414l5 5a.999.999 0 010 1.414l-5 5A.997.997 0 018 16z"
              fill="#5C5F62"
            />
          </svg>
        </button>
      </div>

      <div className="absolute bottom-4 right-0 left-0">
        <div className="flex items-center justify-center gap-2">
          {slides.map((_, i) => (
            <div
              className={`transition-all w-3 h-3 bg-base-200 rounded-full ${
                current === i ? "p-2" : "opacity-50"
              }`}
              key={i}
            ></div>
          ))}
        </div>
      </div>
    </div>
  );
}

export default Carousel;
