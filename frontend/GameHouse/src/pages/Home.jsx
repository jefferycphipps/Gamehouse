import Card from "../components/Card";
import Nav from "../components/Navbar";

function Home() {
  return (
    <>
      <div className="flex-auto">
        <Nav />
      </div>
      <div className=" flex flex-auto w-4/5 mx-auto justify-center">
        <Card />
      </div>
    </>
  );
}

export default Home;
