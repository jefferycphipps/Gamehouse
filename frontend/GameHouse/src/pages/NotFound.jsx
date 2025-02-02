import { Link } from "react-router";

function NotFound() {
  return (
    <div className="mx-auto flex flex-col gap-16">
      <p className="text-5xl font-bold">Page Not Found</p>
      <p className="text-xl">
        We can't seem to find the page you're looking for :(
      </p>
      <Link to={"/"}>
        <button className="btn btn-secondary">Go back home</button>
      </Link>
    </div>
  );
}

export default NotFound;
