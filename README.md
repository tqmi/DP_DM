# DP_DM
Requirements:
Regular Client :
	- upload documents to the storage bucket
	- retrieve past documents (both uploaded and received from other parties)
	- request new documents from other parties (from a list of available documents)
	- provide requested documents to other parties

Government Client:
	- create/update list with all available document templates/models
	- create/update list with all available documents for request
	- view all emited documents
	- view and answer document requests
	- request documents from other parties

Server:
	- authenticate clients
	- limit access based on access level
	- sign/verify documents for clients
	- store/provide client documents
	- keep document flow status (maybe)