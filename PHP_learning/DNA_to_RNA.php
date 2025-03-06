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
<body>
    <div class="d-flex container-fluid justify-content-center align-items-center vh-100 bg-dark ">
        <div id="box" class="bg-gray bg-gradient rounded p-4">
            <form action="DNA_to_RNA.php" method="GET">
                <div id="form-elements" class="">
                    <h2><label for="dna-string" class="text-info">Transcribing DNA into RNA</label></h2>
                    <input id="dna-string" name="dna_string" type="text" placeholder="Enter some valid DNA">
                    <button type="submit" class="btn btn-outline-secondary">Submit</button>
                </div>
            </form>
            <?php
                if (isset($_GET["dna_string"]) && !empty($_GET["dna_string"])) {
                    $dna_string = $_GET["dna_string"];
                    $rna_string = str_replace("T", "U", $dna_string);
                    echo "<p class='text-white text-center'>RNA: $rna_string</p>";
                } else {
                    echo "<p class='text-white text-center'>Please enter a valid DNA string.</p>";
                }
            ?>
        </div>
    </div>

    
</body>
</html>