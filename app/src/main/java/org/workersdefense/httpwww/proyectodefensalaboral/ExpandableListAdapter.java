***REMOVED***

***REMOVED***
import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
***REMOVED***
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
***REMOVED***

public class ExpandableListAdapter extends BaseExpandableListAdapter {
    // Adapted from http://www.androidhive.info/2013/07/android-expandable-list-view-tutorial/
    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<String>> _listDataChild;

    public ExpandableListAdapter(Context context, List<String> listDataHeader,
                                 HashMap<String, List<String>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
***REMOVED***

***REMOVED***
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
***REMOVED***

***REMOVED***
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
***REMOVED***

***REMOVED***
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, null);
    ***REMOVED***

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.lblListItem);

        txtListChild.setText(childText);
        return convertView;
***REMOVED***

***REMOVED***
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
***REMOVED***

***REMOVED***
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
***REMOVED***

***REMOVED***
    public int getGroupCount() {
        return this._listDataHeader.size();
***REMOVED***

***REMOVED***
    public long getGroupId(int groupPosition) {
        return groupPosition;
***REMOVED***

***REMOVED***
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.violation_list_group, null);
    ***REMOVED***

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        lblListHeader.setText(headerTitle);

        return convertView;
***REMOVED***

***REMOVED***
    public boolean hasStableIds() {
        return false;
***REMOVED***

***REMOVED***
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
***REMOVED***
***REMOVED***
