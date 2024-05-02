<?php
include 'conn.php';

$response=array();

$sql="SELECT * FROM `users`";
$result=mysqli_query($conn,$sql);

if(mysqli_num_rows($result)>0)
{
    $response['Status']=1;
    $response['users']=array();
    while($row=mysqli_fetch_assoc($result))
    {
        $contact=array();
        $contact['id']=$row['id'];
        $contact['name']=$row['name'];
        $contact['number']=$row['number'];
        $contact['city']=$row['city'];
        $contact['country']=$row['country'];
        $contact['email']=$row['email'];
        $contact['password']=$row['password'];
        
        array_push($response['users'],$contact);
    }
}
else{
    $response['Status']=0;
    $response['Message']="No data found";
}

echo json_encode($response);

?>
