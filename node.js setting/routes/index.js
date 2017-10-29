var express = require('express');
var router = express.Router();

/* GET home page. */

var db = require('/Users/CRK/node-postgres-promises/queries.js');
router.get('/', function(req, res, next) {
  res.render('index', { title: 'ulpa' });
});


router.get('/api/schools/language/:language_name', db.getSingleLanguage);
router.get('/api/schools/states', db.getAllStates);
router.get('/api/schools/states/finduniversity/:state',db.getUniversityInState);
router.get('/api/schools/states/:findingstate/:findinguniversity',db.getStateUniversityLanguage);
router.get('/api/schools/:statename', db.getAllStateData);
router.get('/api/schools/languagelist/alllanguage', db.getLanguageList);
router.get('/api/schools/allLanguageDetail/languageList/findall/all',db.getAllLanguageDetail);




module.exports = router;
