var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  //we want to do a mysql call to get the desc by date and limit 5-10 of reviews to pass into
  //index to display the latest 5-10 reviews
  
console.log("checking success", req.query.success);
if(req.session && req.session.username && req.session.username.length){
  res.render('index', { title: 'Movie List', success: "true", loggedIn: req.session.username});
  return;
}
  // if signed change login button to sign out
  res.render('index', { title: 'Movie List',success: "false" });
    console.log("pasta");
});

router.get('/register', function(req,res,next){
  res.render('register', { title: 'Register' });
    console.log("linguini");
});
module.exports = router;
