import { Link } from "react-router";

function NotFound() {
  return (
    <div className="mx-auto flex flex-col gap-16">
      <p className="text-5xl font-bold">404 - Page Not Found</p>
      <p className="text-xl">
        Sorry, the page you are looking for could not be found :(
      </p>
      <Link to={"/"}>
        <button className="btn btn-info">Go back home</button>
      </Link>
    </div>
  );
}

export default NotFound;
