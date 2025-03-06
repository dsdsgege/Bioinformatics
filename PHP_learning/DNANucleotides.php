<?php
  if(session_status() === PHP_SESSION_NONE){
    session_start();
  }
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</head>
<body class="bg-dark">
  <div class="d-flex justify-content-center align-items-center min-vh-100 ">
    <div id="outside-box" class="vh-80 bg-secondary bg-gradient rounded p-5">
      <form method="POST">
        <div class="text-center ">
          <h2><label for="dna-string" class="form-label">Counting DNA Nucleotides</label></h2>
          
          <input id="dna-string" class="rounded" name="dna_string" placeholder="Enter the DNA string.">
          <div class="form-text"></div>
          <button type="submit" class="btn btn-success">Submit</button>
          <?php 
          
          if(isset($_POST["dna_string"])) {
                $dna_string = $_POST["dna_string"];
                $ACounter = $CCounter = $GCounter = $TCounter = 0;

                foreach(str_split($dna_string) as $ch) {
                  switch($ch) {
                    case 'A':
                      $ACounter++;
                      break;
                    case 'C':
                      $CCounter++;
                      break;
                    case 'G':
                      $GCounter++;
                      break;
                    case 'T':
                      $TCounter++;
                      break;
                    default:
                      $_SESSION["alert_string"] = "<script> alert('The DNA string is not valid');</script>";
                      exit;
                  }
                }

                // we need a session and store the variables so we can make an alert appear only once
                $_SESSION["alert_string"] = "A: $ACounter, C: $CCounter, G: $GCounter, T: $TCounter";

                
              }
              
              if(isset($_SESSION["alert_string"])) {
                echo "<script>alert('" . $_SESSION['alert_string'] . "');</script>";
                unset($_SESSION["alert_string"]);
                header("Location: " . $_SERVER['PHP_SELF']);
                exit;
              }
          ?>
        </div>
      </form>
    </div>
  </div>
</body> 
</html>