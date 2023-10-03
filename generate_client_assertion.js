const jwt = require('jsonwebtoken');
const fs = require('fs');

const clientId = 'h30sf2yQqYyMYv2LfPlb2TtJMOqKGbpg';     // Replace with your client ID
const audience = 'https://dev-436yn26dgvwt7b0g.us.auth0.com/oauth/token'; // Replace with the token endpoint URL
const jwtId = 'ae9403ee-67f5-40bf-a10e-149a13116314'; // Replace with a unique identifier
const expirationTime = Math.floor(Date.now() / 1000) + 120; // 1 hour from now

const privateKey = fs.readFileSync('decrypted_private_key.pem', 'utf8');
const payload = {
  iss: clientId,
  sub: clientId,
  aud: audience,
  jti: jwtId,
  exp: expirationTime
};

const clientAssertion = jwt.sign(payload, privateKey, { algorithm: 'RS256'});
console.log(clientAssertion);
/** VERIFICATION */
const publicKey = fs.readFileSync('public_key.pem', 'utf8');
try {
    const decoded = jwt.verify(clientAssertion, publicKey, { algorithms: ['RS256'] });
    console.log('Signature is valid', decoded);
} catch (err) {
    console.log('Signature verification failed', err);
}

/** CALL */
var access_token;
const axios = require('axios');
axios({
  method: 'post',
  url: 'https://dev-436yn26dgvwt7b0g.us.auth0.com/oauth/token',
  headers: {
    'Content-Type': 'application/x-www-form-urlencoded',
  },
  data: `grant_type=client_credentials&scope=read:users&audience=https://dev-436yn26dgvwt7b0g.us.auth0.com/api/v2/&client_assertion_type=urn:ietf:params:oauth:client-assertion-type:jwt-bearer&client_assertion=${clientAssertion}`
})
.then(response => {
  console.log('Token Response:', response.data);
  access_token = response.data.access_token; 

  console.log("calling users api with access_token: ", access_token);
  axios.get('https://dev-436yn26dgvwt7b0g.us.auth0.com/api/v2/users', {
    headers: {
      'Authorization': `Bearer ${access_token}`
    }
  })
  .then(response => {
    console.log("Output/s: ", response.data);
  })
  .catch(error => {
    console.error('Error fetching users:', error);
  });  
})
.catch(error => {
  console.error('Error fetching token:', error);
});



